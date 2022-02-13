package br.com.jogosusados.network

import br.com.jogosusados.BuildConfig
import br.com.redcode.base.extensions.toLogcat
import br.com.redcode.base.utils.Constants.ERROR_API_CLEAN_AND_FORCE_LOGIN
import br.com.redcode.easyreftrofit.library.AbstractNetworkAndErrorHandler
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import com.squareup.moshi.Moshi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException


class NetworkAndErrorHandler(
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : AbstractNetworkAndErrorHandler(), KoinComponent {

    private val moshi: Moshi by inject()
    private val message by lazy { "erro de internet" }

    override fun handle(exception: Throwable) {
        when (exception) {
            is HttpException -> checkIfTokenHasExpired(exception)
            else -> super.handle(exception)
        }
    }

    private fun checkIfTokenHasExpired(exception: HttpException) {
        when (val networkError = exception.code()) {
            HTTP_STATUS_CODE_TOKEN_EXPIRED -> onTokenExpired(networkError)
            HTTP_STATUS_CODE_UNAUTHORIZED_ACCESS -> onUnauthorizedAccess(networkError)
            else -> super.handle(exception)
        }
    }

    private fun onUnauthorizedAccess(networkError: Int) {
        callbackNetworkRequest?.onNetworkHttpError(
            errorHandled = ErrorHandled(
                message = "",
                networkError = networkError,
                actionAPI = networkError
            )
        )
    }

    private fun onTokenExpired(networkError: Int) {
        callbackNetworkRequest?.onNetworkHttpError(
            errorHandled = ErrorHandled(
                message = "Por favor, faça login novamente",
                networkError = networkError,
                actionAPI = ERROR_API_CLEAN_AND_FORCE_LOGIN
            )
        )
    }

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

    companion object {
        const val HTTP_STATUS_CODE_UNAUTHORIZED_ACCESS = 401
        private const val HTTP_STATUS_CODE_TOKEN_EXPIRED = 403
    }
}
