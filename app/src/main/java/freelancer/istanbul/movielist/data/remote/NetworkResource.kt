package freelancer.istanbul.movielist.data.remote

import org.json.JSONObject
import retrofit2.Response

abstract class NetworkResource {

    suspend fun <T> apiCall(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(getErrorResponse(response))
        } catch (exception: Exception) {
            exception.printStackTrace()
            return Resource.Error(getErrorResponse<T>(null))
        }
    }


    private fun <T> getErrorResponse(response: Response<T>?): ErrorResponseModel {
        try {
            if (response?.code() == 422) {
                response.errorBody()?.let {
                    val jsonObject = JSONObject(it.string())
                    return ErrorResponseModel(
                        jsonObject.getString("title"),
                        jsonObject.getString("message"),
                        jsonObject.getString("okay"),
                        jsonObject.getString("cancel"),
                    )
                }
            }

            return ErrorResponseModel("Uyarı", "Sunucuya bağlanılamıyor. Daha sonra tekrar deneyin", "Tamam")
        }
        catch (exception: Exception) {
            return ErrorResponseModel("Uyarı", "Bir hata oldu. Lütfen daha sonra deneyin", "Tamam")
        }
    }
}