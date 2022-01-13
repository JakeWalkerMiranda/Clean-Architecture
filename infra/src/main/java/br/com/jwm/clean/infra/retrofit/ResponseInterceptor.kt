package br.com.jwm.clean.infra.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)

        var tryCount = 0
        while (response.needToRetry() && tryCount < MAX_TRY_COUNT) {
            tryCount++

            val newRequest = request.newBuilder()
                .header("Authorization", "Bearer")
                .build()

            response = chain.proceed(newRequest)
        }

        return response
    }

    private fun Response.needToRetry() =
        (code == UNAUTHORIZED_CODE ||
                code == FORBIDDEN_CODE ||
                code == CLIENT_CLOSED_CODE ||
                code == BAD_REQUEST_CODE)

    companion object {
        const val MAX_TRY_COUNT = 3
        const val BAD_REQUEST_CODE = 400
        const val UNAUTHORIZED_CODE = 401
        const val FORBIDDEN_CODE = 403
        const val CLIENT_CLOSED_CODE = 499
    }
}