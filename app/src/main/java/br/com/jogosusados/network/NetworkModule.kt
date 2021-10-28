package br.com.jogosusados.network

import br.com.jogosusados.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val CONNECT_TIMEOUT_IN_SECONDS: Long = 10
    private const val READ_TIMEOUT_IN_SECONDS: Long = 10
    private const val BASE_URL = "http://192.168.100.28:8080/"

    private fun createRetrofit(
        httpClient: OkHttpClient,
        baseURL: String,
        converterFactory: Converter.Factory
    ) = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(converterFactory)
        .client(httpClient)
        .build()

    private fun createLoggerInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private fun createService(vararg interceptors: Interceptor) = OkHttpClient()
        .newBuilder()
        .apply {
            connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            interceptors.forEach { interceptor -> addInterceptor(interceptor) }
        }.build()

    object NetworkRegular : Qualifier {
        override val value: QualifierValue
            get() = "ApiNormal"
    }

    val instance = module {
        single { createService(createLoggerInterceptor(), ProxyInterceptor()) }

        factory<Retrofit>(NetworkRegular) {
            createRetrofit(
                httpClient = get(),
                baseURL = BASE_URL,
                converterFactory = MoshiConverterFactory.create()
            )
        }
    }
}