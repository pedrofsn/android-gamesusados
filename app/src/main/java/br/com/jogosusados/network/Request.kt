package br.com.jogosusados.network

import br.com.redcode.base.interfaces.Payload
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import retrofit2.HttpException

object Request {

    infix fun <RetrofitAPI> with(service: RetrofitAPI): RetrofitAPI = service

    @Throws
    suspend inline infix fun <TypePayload : Payload<TypeModel>, TypeModel, RetrofitAPI> RetrofitAPI.call(
        crossinline request: suspend RetrofitAPI.() -> TypePayload,
    ): Pair<TypeModel?, Exception?> {
        return try {
            val result = request(this)
            Pair(result.toModel(), null)
        } catch (exception: Exception) {
            Pair(null, exception)
        }
    }

    @Throws
    suspend inline infix fun <TypePayload : List<Payload<TypeModel>>, TypeModel, RetrofitAPI> RetrofitAPI.callList(
        crossinline request: suspend RetrofitAPI.() -> TypePayload,
    ): Pair<List<TypeModel>?, Exception?> {
        return try {
            val result = request(this)
            Pair(result.map { it.toModel() }, null)
        } catch (exception: Exception) {
            Pair(null, exception)
        }
    }

    infix fun <TypeModel> Pair<TypeModel?, Exception?>.handled(callbackNetworkRequest: CallbackNetworkRequest?): TypeModel? {
        if (first != null) {
            return first
        } else if (second != null) {
            when (val exception = second) {
                is HttpException -> callbackNetworkRequest onHttpException exception
                is UnknownHostException -> throw exception
                is ConnectException -> onConnectionRefused(callbackNetworkRequest)
                is SocketTimeoutException -> onConnectionRefused(callbackNetworkRequest)
                else -> exception?.printStackTrace()
            }
        }
        return null
    }

    private infix fun CallbackNetworkRequest?.onHttpException(exception: HttpException) {
        val handler = get<NetworkAndErrorHandler>(NetworkAndErrorHandler::class.java) {
            parametersOf(this)
        }
        handler.handle(exception)
        exception.printStackTrace()
    }

    private fun onConnectionRefused(callbackNetworkRequest: CallbackNetworkRequest?) {
        val errorHandled = ErrorHandled(
            message = "Verifique sua conexão com a internet",
            networkError = 123
        )
        callbackNetworkRequest?.onNetworkHttpError(errorHandled)
    }

    inline infix fun Pair<Any?, HttpException?>.onFailure(
        crossinline handleFailureManual: ((Throwable) -> Unit),
    ): Pair<Any?, HttpException?> {
        val httpException = this.second!!
        handleFailureManual.invoke(httpException)
        return this
    }

    inline infix fun Pair<Any?, HttpException?>.onManual(
        crossinline handleErrorManual: ((String?) -> Unit),
    ): Pair<Any?, HttpException?> {
        val httpException = this.second!!
        val errorBody = httpException.response()?.errorBody()?.string()
        handleErrorManual.invoke(errorBody)
        return this
    }


}
