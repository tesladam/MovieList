package freelancer.istanbul.movielist.ui.movie_list


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
data class MovieListResponseModel(
    val page: Int,
    var results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) {
    @Parcelize
    @Keep
    data class Result(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    ): Parcelable
}