package br.com.jogosusados.features.login

import androidx.databinding.ObservableField
import br.com.jogosusados.features.login.repository.LoginRepository
import br.com.jogosusados.features.login.repository.TOKEN_TEMP
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class LoginViewModel(callback: CallbackNetworkRequest?) : BaseViewModel(), KoinComponent {

    private val loginRepository by inject<LoginRepository>(LoginRepository::class.java) {
        parametersOf(this)
    }

    val username = ObservableField<String>()
    val password = ObservableField<String>()

    fun checkIfHasToken() {
        if (TOKEN_TEMP.isNotBlank()) {
            sendEventToUI("onLoggedIn")
        }
    }

    fun login() {
        process("onLoggedIn") {
            loginRepository.login(
                email = username.get().orEmpty(),
                password = password.get().orEmpty()
            )
        }
    }
}