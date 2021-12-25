package br.com.jogosusados.network

import br.com.jogosusados.BuildConfig
import br.com.redcode.base.extensions.toLogcat
import br.com.redcode.easyreftrofit.library.AbstractNetworkAndErrorHandler
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import com.squareup.moshi.Moshi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class NetworkAndErrorHandler(
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : AbstractNetworkAndErrorHandler(), KoinComponent {

    private val moshi: Moshi by inject()
    private val message by lazy { "erro de internet" }

    override fun catchedException(exception: Throwable) {
        when {
            BuildConfig.DEBUG.not() -> "Throwable message: ${exception.message}".toLogcat()
            else -> exception.printStackTrace()
        }
    }

    override fun parseBodyError(errorBodyAsString: String, networkError: Int): ErrorHandled {
        return try {
            val adapter = moshi.adapter(CustomErrorPayload::class.java)
            val payloadError: CustomErrorPayload? = adapter.fromJson(errorBodyAsString)
            val modelError = payloadError?.toModel(networkError)
            modelError ?: ErrorHandled(message = message, networkError = networkError)
        } catch (e: Exception) {
            val error = CustomErrorPayload(
                message = String.format(message, networkError),
                msg_dev = e.message.orEmpty()
            )
            val errorHandled = error.toModel(networkError)
            "Error in method 'parseBodyError' from class 'NetworkAndErrorHandler.kt': ${e.message}".toLogcat()
            errorHandled
        }
    }
}
