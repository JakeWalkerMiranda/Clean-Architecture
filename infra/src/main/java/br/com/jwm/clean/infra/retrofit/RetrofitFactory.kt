package br.com.jwm.clean.infra.retrofit

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    private const val CONNECT_TIMEOUT = 10L
    private const val WRITE_TIMEOUT = 10L
    private const val READ_TIMEOUT = 30L

    fun getInstance(
        url: String,
        moshi: Moshi,
        responseInterceptor: Interceptor? = null
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(getOkHttpClient(responseInterceptor))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    private fun getOkHttpClient(responseInterceptor: Interceptor? = null): OkHttpClient {
        val okHttp = OkHttpClient.Builder()
            .apply { responseInterceptor?.also { addInterceptor(it) } }
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

        return okHttp.build()
    }
}