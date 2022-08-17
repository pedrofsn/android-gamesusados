package br.com.jogosusados.network

import br.com.jogosusados.BuildConfig
import br.com.jogosusados.features.storage.Storage
import br.com.jogosusados.features.storage.StorageConstants.TOKEN
import br.com.redcode.base.extensions.extract
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class ProxyInterceptor(private val storage: Storage) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val httpUrlBuilder = originalHttpUrl.newBuilder()
        val httpUrl = httpUrlBuilder.build()

        val token = storage.getString(TOKEN, "")

        val requestBuilder = original
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("osversion", extract safe BuildConfig.VERSION_NAME)
            .addHeader("osname", "android")
            .addHeader("Authorization", "Bearer $token")
            .url(httpUrl)

        Timber.d("Using token '$token'")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
