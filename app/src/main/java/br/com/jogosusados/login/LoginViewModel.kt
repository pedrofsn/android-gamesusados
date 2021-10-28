package br.com.jogosusados.login

import androidx.databinding.ObservableField
import br.com.jogosusados.login.repository.LoginRepository
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class LoginViewModel : BaseViewModel() {

    private val loginRepository by inject<LoginRepository>(LoginRepository::class.java) {
        parametersOf(this)
    }

    val username = ObservableField<String>()
    val password = ObservableField<String>()

    fun validateForm() = sendEventToUI("validateForm")

    fun login() {
        launch {
            loginRepository.login(
                email = username.get().orEmpty(),
                password = password.get().orEmpty()
            )
        }
        showMessage(username.get() + " => " + password.get())
    }
}