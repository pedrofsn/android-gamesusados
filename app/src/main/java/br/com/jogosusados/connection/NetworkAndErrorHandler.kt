package br.com.jogosusados.connection

import br.com.jogosusados.BuildConfig
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.extensions.toLogcat
import br.com.redcode.base.rest.PayloadError
import br.com.redcode.easyreftrofit.library.AbstractNetworkAndErrorHandler
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import com.squareup.moshi.Moshi
import org.koin.java.KoinJavaComponent.inject

fun PayloadError.toModel(networkError: Int) = ErrorHandled(
    message = extract safe msg,
    actionAPI = extract safe acao,
    networkError = networkError,
    id = extract safe id
)

class NetworkAndErrorHandler(
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : AbstractNetworkAndErrorHandler() {

    private val moshi: Moshi by inject(Moshi::class.java)
    private val message by lazy { "erro de internet" }

    override fun catchedException(exception: Throwable) {
        when {
            BuildConfig.DEBUG.not() -> "Throwable message: ${exception.message}".toLogcat()
            else -> exception.printStackTrace()
        }
    }

    override fun parseBodyError(errorBodyAsString: String, networkError: Int): ErrorHandled {
        return try {
            val adapter = moshi.adapter(PayloadError::class.java)
            val payloadError: PayloadError? = adapter.fromJson(errorBodyAsString)
            val modelError = payloadError?.toModel(networkError)
            modelError ?: ErrorHandled(message = message, networkError = networkError)
        } catch (e: Exception) {
            val error =
                PayloadError(msg = String.format(message, networkError), msg_dev = e.message)
            val errorHandled = error.toModel(networkError)
            "Error in method 'parseBodyError' from class 'NetworkAndErrorHandler.kt': ${e.message}".toLogcat()
            errorHandled
        }
    }
}