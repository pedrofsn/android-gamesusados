package br.com.jogosusados.network

import br.com.redcode.base.interfaces.Payload
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import retrofit2.HttpException

suspend fun <TypePayload : Payload<TypeModel>, TypeModel, RetrofitAPI> Requestable.doRequest(
    //callbackNetworkRequest: CallbackNetworkRequest? = null,
    handleErrorManual: ((String?) -> Unit)? = null,
    handleFailureManual: ((Throwable) -> Unit)? = null,
    service: RetrofitAPI,
    request: suspend RetrofitAPI.() -> TypePayload
): TypeModel? {
    return try {
        val result = request(service)
        result.toModel()
    } catch (exception: Exception) {
        when (exception) {
            is HttpException -> onHttpException(
                httpException = exception,
                handleErrorManual = handleErrorManual,
                callbackNetworkRequest = callbackNetworkRequest,
                handleFailureManual = handleFailureManual
            )
            is UnknownHostException -> throw exception
            is SocketTimeoutException -> {
                val errorHandled = ErrorHandled(
                    message = "deu ruim",
                    networkError = 123
                )
                callbackNetworkRequest?.onNetworkHttpError(errorHandled)
            }
            else -> exception.printStackTrace()
        }

        return null
    }
}

private fun onHttpException(
    httpException: HttpException,
    handleErrorManual: ((String?) -> Unit)?,
    callbackNetworkRequest: CallbackNetworkRequest?,
    handleFailureManual: ((Throwable) -> Unit)?
) {
    val code = httpException.code()
    val errorBody = httpException.response()?.errorBody()?.string()
    val handler by inject<NetworkAndErrorHandler>(NetworkAndErrorHandler::class.java) {
        parametersOf(callbackNetworkRequest)
    }
    when {
        handleErrorManual == null && errorBody != null -> {
            handler.handleErrorJSONWithStatusCodeHTTP(errorBody, code)
        }
        handleErrorManual != null -> handleErrorManual.invoke(errorBody)
        handleFailureManual != null -> handleFailureManual.invoke(httpException)
        else -> {
            handler.handle(httpException)
            httpException.printStackTrace()
        }
    }
}
