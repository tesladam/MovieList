package freelancer.istanbul.movielist.ui.movie_detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import freelancer.istanbul.movielist.R
import freelancer.istanbul.movielist.databinding.FragmentMovieDetailBinding
import freelancer.istanbul.movielist.ui.base.BaseFragment
import freelancer.istanbul.movielist.util.ApiUtil.BASE_IMG_URL
import freelancer.istanbul.movielist.util.extensions.url


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater, container, false)
    }

    override fun FragmentMovieDetailBinding.onBindView(savedInstanceState: Bundle?) {
        val args: MovieDetailFragmentArgs by navArgs()

        movieDetailBannerBack.setOnClickListener { helperBackFragment() }

        movieDetailImg.url(BASE_IMG_URL + args.movie.backdrop_path, true)
        movieDetailTitle.text = args.movie.title
        movieDetailRating.text = args.movie.vote_average.toString()
        movieDetailOverview.text = args.movie.overview

        if (args.movie.overview.isEmpty())
            movieDetailOverview.visibility = GONE

        val color = ContextCompat.getColor(this.root.context,
            if (args.movie.vote_average >= 9) R.color.yesil
            else if (args.movie.vote_average >= 7) R.color.turuncu
            else R.color.kirmizi
        )

        movieDetailRating.setTextColor(color)
        movieDetailRating2.setTextColor(color)

        movieDetailRatingBar.apply {
            this.rating = 1F
            this.progressTintList = ColorStateList.valueOf(color)
            this.secondaryProgressTintList = ColorStateList.valueOf(color)
        }
    }

}