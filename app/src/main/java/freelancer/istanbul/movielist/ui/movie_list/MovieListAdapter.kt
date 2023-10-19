package freelancer.istanbul.movielist.ui.movie_list

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import freelancer.istanbul.movielist.R
import freelancer.istanbul.movielist.databinding.ItemMovieListBinding
import freelancer.istanbul.movielist.ui.base.BaseRecyclerViewAdapter
import freelancer.istanbul.movielist.ui.base.BaseViewHolder
import freelancer.istanbul.movielist.util.ApiUtil.BASE_IMG_URL
import freelancer.istanbul.movielist.util.extensions.url
import java.time.LocalDate

class MovieListAdapter: BaseRecyclerViewAdapter<MovieListResponseModel.Result>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieListResponseModel.Result> {
        val viewBinding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(viewBinding)
    }

    private inner class MovieListViewHolder(private val binding: ItemMovieListBinding) : BaseViewHolder<MovieListResponseModel.Result>(binding) {

        override fun bindView(data: MovieListResponseModel.Result) {
            with(binding) {
                itemMovieListImg.url(BASE_IMG_URL + data.poster_path, true)
                itemMovieListTitle.text = data.title
                itemMovieListYear.text = LocalDate.parse(data.release_date).year.toString()
                itemMovieListRating.text = data.vote_average.toString()


                val color = ContextCompat.getColor(this.root.context,
                    if (data.vote_average >= 9) R.color.yesil
                    else if (data.vote_average >= 7) R.color.turuncu
                    else R.color.kirmizi
                )

                itemMovieListRating.setTextColor(color)
                itemMovieListRating2.setTextColor(color)

                itemMovieListRatingBar.apply {
                    this.rating = 1F
                    this.progressTintList = ColorStateList.valueOf(color)
                    this.secondaryProgressTintList = ColorStateList.valueOf(color)
                }
            }
        }
    }
}