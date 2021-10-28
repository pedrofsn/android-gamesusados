package br.com.jogosusados.features.login

import android.view.View
import br.com.jogosusados.R
import br.com.jogosusados.databinding.ActivityLoginBinding
import br.com.redcode.base.mvvm.restful.databinding.domain.ActivityMVVM
import br.com.redcode.easyvalidation.Validate

class LoginActivity : ActivityMVVM<ActivityLoginBinding, LoginViewModel>() {

    override val classViewModel = LoginViewModel::class.java
    override val layout = R.layout.activity_login

    override fun afterOnCreate() = Unit

    fun validateForm(view: View?) {
        if (Validate.isFilled(
                binding.textInputEditTextUsername,
                binding.textInputEditTextPassword
            )
        ) viewModel.login()
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onLoggedIn" -> if (obj != null && obj is String) toast(obj)
            else -> super.handleEvent(event, obj)
        }
    }
}