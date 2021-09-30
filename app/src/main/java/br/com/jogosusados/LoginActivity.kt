package br.com.jogosusados

import br.com.jogosusados.databinding.ActivityLoginBinding
import br.com.redcode.base.mvvm.restful.databinding.domain.ActivityMVVM

class LoginActivity : ActivityMVVM<ActivityLoginBinding, LoginViewModel>() {

    override val classViewModel = LoginViewModel::class.java
    override val layout = R.layout.activity_login

    override fun afterOnCreate() {
        viewModel.load()
    }

}