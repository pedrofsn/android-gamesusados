package br.com.jogosusados.features.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentLoginBinding
import br.com.jogosusados.features.login.LoginViewModel
import br.com.jogosusados.features.login.di.LoginModule
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyvalidation.Validate.isFilled
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class LoginFragment : FragmentMVVMDataBinding<FragmentLoginBinding, LoginViewModel>() {

    override val classViewModel = LoginViewModel::class.java
    override val layout = R.layout.fragment_login

    private val loginViewModel: LoginViewModel by viewModel {
        parametersOf(this@LoginFragment.requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(LoginModule.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = loginViewModel
        defineMVVM(this)
        return binding.root
    }

    override fun afterOnCreate() {
        viewModel.checkIfHasToken()
        binding.button.setOnClickListener { validateForm() }
        binding.buttonRegister.setOnClickListener { openRegisterScreen() }
        binding.imageViewLogo.load(R.mipmap.ic_launcher)
    }

    private fun openRegisterScreen() {
        val directions = LoginFragmentDirections.actionLoginFragmentToUserRegisterFragment()
        findNavController().navigate(directions)
    }

    private fun validateForm() {
        if (isFilled(binding.textInputEditTextUsername, binding.textInputEditTextPassword)) {
            viewModel.login()
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onLoggedIn" -> onLoggedIn()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onLoggedIn() {
        val directions = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(LoginModule.instance)
    }
}
