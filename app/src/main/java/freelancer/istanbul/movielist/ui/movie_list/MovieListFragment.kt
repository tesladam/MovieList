package freelancer.istanbul.movielist.ui.movie_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import freelancer.istanbul.movielist.R
import freelancer.istanbul.movielist.data.remote.Resource
import freelancer.istanbul.movielist.databinding.FragmentMovieListBinding
import freelancer.istanbul.movielist.ui.base.BaseFragment


@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    private val viewModel: MovieListViewModel by viewModels()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMovieListBinding {
        return FragmentMovieListBinding.inflate(inflater, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun FragmentMovieListBinding.onBindView(savedInstanceState: Bundle?) {

        val movieListAdapter = MovieListAdapter().apply {
            this.setOnItemClickListener { _, data ->
                helperGoToFragment(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(data))
            }
        }

        movieListRecyc.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.setHasFixedSize(true)
            this.adapter = movieListAdapter
        }

        viewModel.movie_list_live_data.observe(this@MovieListFragment) {
            when(it) {
                is Resource.Loading -> { }
                is Resource.Success -> {
                    movieListRefresh.isRefreshing = false

                    movieListAdapter.apply {
                        this.setDataList(it.data.results)
                        this.notifyDataSetChanged()
                        this.setOnLoadMoreListener({ viewModel.nextPage() }, 2)
                    }
                }
                is Resource.Error -> {
                    movieListRefresh.isRefreshing = false

                    logGoster(it.errorResponseModel.toString())
                }
            }
        }

        viewModel.refresh()

        movieListRefresh.setOnRefreshListener {
            viewModel.refresh(1)
        }
    }

}