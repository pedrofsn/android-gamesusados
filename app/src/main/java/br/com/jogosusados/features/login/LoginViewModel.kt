package br.com.jogosusados.features.login

import androidx.databinding.ObservableField
import br.com.jogosusados.features.login.repository.LoginRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class LoginViewModel(callback: CallbackNetworkRequest?) : BaseViewModel(), KoinComponent {

    private val loginRepository: LoginRepository by inject {
        parametersOf(this@LoginViewModel, callback)
    }

    val username = ObservableField<String>()
    val password = ObservableField<String>()

    fun checkIfHasToken() = process("onLoggedIn") {
        loginRepository.reuseToken()
    }.also {
        username.set("user1@gmail.com")
        password.set("123456")
    }

    fun login() = process("onLoggedIn") {
        loginRepository.login(
            email = username.get().orEmpty(),
            password = password.get().orEmpty()
        )
    }
}
