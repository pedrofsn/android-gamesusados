package br.com.jogosusados.network

import br.com.redcode.base.interfaces.Payload
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Request {

    infix fun <RetrofitAPI> with(service: RetrofitAPI): RetrofitAPI {
        return service
    }

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

    infix fun <TypeModel> Pair<TypeModel?, Exception?>.handle(callbackNetworkRequest: CallbackNetworkRequest?): TypeModel? {
        if (first != null) {
            return first
        } else if (second != null) {
            when (val exception = second) {
                is HttpException -> {
                    val handler by KoinJavaComponent.inject<NetworkAndErrorHandler>(
                        NetworkAndErrorHandler::class.java) {
                        parametersOf(callbackNetworkRequest)
                    }
                    handler.handle(exception)
                    exception.printStackTrace()
                }
                is UnknownHostException -> throw exception
                is SocketTimeoutException -> {
                    val errorHandled = ErrorHandled(
                        message = "deu ruim",
                        networkError = 123
                    )
                    callbackNetworkRequest?.onNetworkHttpError(errorHandled)
                }
                else -> exception?.printStackTrace()
            }

        }

        return null
    }

    inline infix fun Pair<Any?, HttpException?>.onFailure(
        crossinline handleFailureManual: ((Throwable) -> Unit),
    ) {
        val httpException = this.second!!
        handleFailureManual.invoke(httpException)
    }

    inline infix fun Pair<Any?, HttpException?>.onManual(
        crossinline handleErrorManual: ((String?) -> Unit),
    ) {
        val httpException = this.second!!
        val errorBody = httpException.response()?.errorBody()?.string()
        handleErrorManual.invoke(errorBody)
    }


}