package az.movie.az_movie.util.response_handler

import retrofit2.Response

suspend fun <T> handleResponse(apiCall: suspend () -> Response<T>): Resource<T> {
    try {
        val result = apiCall()
        val body = result.body()
        if (result.isSuccessful && body != null) {
            body.let {
                return Resource.Success(body)
            }
        }
        return Resource.Error(null , result.message())
    } catch (e: Exception) {
        return Resource.Error(null , e.message ?: e.toString())
    }
}

