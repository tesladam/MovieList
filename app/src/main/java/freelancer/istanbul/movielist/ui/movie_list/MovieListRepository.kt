package freelancer.istanbul.movielist.ui.movie_list

import dagger.hilt.android.scopes.ViewModelScoped
import freelancer.istanbul.movielist.data.local.SharedPrefsManager
import freelancer.istanbul.movielist.data.remote.ApiInterface
import freelancer.istanbul.movielist.data.remote.NetworkResource
import freelancer.istanbul.movielist.data.remote.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ViewModelScoped
class MovieListRepository @Inject constructor(private val apiInterface: ApiInterface, private var mSharedPrefsManager: SharedPrefsManager) : NetworkResource() {

    fun get_movie_list(page: Int) = flow {
        emit(Resource.Loading)

        val apiResponse = apiCall { apiInterface.get_movie_list(page) }

        if (apiResponse is Resource.Success) {
            mSharedPrefsManager.setResponse("MovieListResponseModel", apiResponse.data)
        }
        else if (apiResponse is Resource.Error) {
            mSharedPrefsManager.getResponse<MovieListResponseModel>("MovieListResponseModel")?.let {
                emit(Resource.Success(it))
            }
        }

        emit(apiResponse)
    }.flowOn(Dispatchers.IO)
}