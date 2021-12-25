package br.com.jogosusados.network

import br.com.jogosusados.BuildConfig
import br.com.jogosusados.features.login.repository.TOKEN_TEMP
import br.com.redcode.base.extensions.extract
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ProxyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val httpUrlBuilder = originalHttpUrl.newBuilder()
        val httpUrl = httpUrlBuilder.build()

        val requestBuilder = original
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("osversion", extract safe BuildConfig.VERSION_NAME)
            .addHeader("osname", "android")
            .addHeader("Authorization", "Bearer $TOKEN_TEMP")
            .url(httpUrl)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
