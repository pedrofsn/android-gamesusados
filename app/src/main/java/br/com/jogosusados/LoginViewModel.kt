package br.com.jogosusados

import androidx.databinding.ObservableField
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel

class LoginViewModel : BaseViewModel() {

    val username = ObservableField<String>()
    val password = ObservableField<String>()

    fun validateForm() = sendEventToUI("validateForm")

    fun login() {
        showMessage(username.get() + " => " + password.get())
    }

}