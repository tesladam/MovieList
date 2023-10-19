package freelancer.istanbul.movielist.data.remote

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val errorResponseModel: ErrorResponseModel) : Resource<Nothing>()
}