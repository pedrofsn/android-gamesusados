package br.com.jogosusados

import android.view.View
import br.com.jogosusados.databinding.ActivityLoginBinding
import br.com.redcode.base.mvvm.restful.databinding.domain.ActivityMVVM
import br.com.redcode.easyvalidation.Validate

class LoginActivity : ActivityMVVM<ActivityLoginBinding, LoginViewModel>() {

    override val classViewModel = LoginViewModel::class.java
    override val layout = R.layout.activity_login

    override fun afterOnCreate() {

    }

    fun validateForm(view: View?) {
        if (Validate.isFilled(
                binding.textInputEditTextUsername,
                binding.textInputEditTextPassword
            )
        ) {
            viewModel.login()
        }
    }

}