package br.com.jogosusados.network

import br.com.jogosusados.BuildConfig
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val CONNECT_TIMEOUT_IN_SECONDS: Long = 10
    private const val READ_TIMEOUT_IN_SECONDS: Long = 10
    private const val NAME_BASE_URL = "baseURL"
    private const val TAG_HTTP_CLIENT = "OkHttp"

    private fun createRetrofit(
        httpClient: OkHttpClient,
        baseURL: String,
        converterFactory: Converter.Factory
    ) = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(converterFactory)
        .client(httpClient)
        .build()

    private fun createLoggerInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Timber.tag(TAG_HTTP_CLIENT).d(message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
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
        single { Moshi.Builder().build() }

        factory { (callback: CallbackNetworkRequest?) ->
            NetworkAndErrorHandler(callbackNetworkRequest = callback)
        }

        single { createService(createLoggerInterceptor(), ProxyInterceptor(storage = get())) }
        single(named(NAME_BASE_URL)) { "http://192.168.100.29:8080/" }

        factory<Retrofit>(NetworkRegular) {
            createRetrofit(
                httpClient = get(),
                baseURL = get(named(NAME_BASE_URL)),
                converterFactory = MoshiConverterFactory.create()
            )
        }
    }
}
