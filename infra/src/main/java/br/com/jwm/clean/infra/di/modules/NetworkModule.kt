package br.com.jwm.clean.infra.di.modules

import br.com.jwm.clean.data.datasources.MainDataSource
import br.com.jwm.clean.data.repositories.MainRepositoryImpl
import br.com.jwm.clean.domain.repositories.MainRepository
import br.com.jwm.clean.infra.retrofit.ResponseInterceptor
import br.com.jwm.clean.infra.retrofit.RetrofitClient
import br.com.jwm.clean.infra.retrofit.RetrofitFactory
import br.com.jwm.clean.remote.datasources.MainDataSourceImpl
import br.com.jwm.clean.remote.service.MainService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {

    // Moshi
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // Retrofit
    single {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single { ResponseInterceptor() }

    single(qualifier = named(RetrofitClient.MAIN.name)) {
        RetrofitFactory.getInstance(
            url = RetrofitClient.MAIN.url,
            moshi = get(),
            responseInterceptor = get<ResponseInterceptor>()
        )
    }

    // Service
    single {
        get<Retrofit>(
            qualifier = named(RetrofitClient.MAIN.name)
        ).create(MainService::class.java)
    }

    // Data Source
    single<MainDataSource> {
        MainDataSourceImpl(mainService = get())
    }

    // Repository
    single<MainRepository> {
        MainRepositoryImpl(mainDataSource = get())
    }
}

private const val TIMEOUT = 10L