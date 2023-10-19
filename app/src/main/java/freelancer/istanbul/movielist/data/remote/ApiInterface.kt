package freelancer.istanbul.movielist.data.remote

import freelancer.istanbul.movielist.ui.movie_list.MovieListResponseModel
import freelancer.istanbul.movielist.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("3/discover/movie")
    suspend fun get_movie_list(
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "tr-TR",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieListResponseModel>

}

