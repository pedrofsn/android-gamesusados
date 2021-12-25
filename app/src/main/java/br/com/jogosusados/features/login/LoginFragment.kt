package br.com.jogosusados.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentLoginBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyvalidation.Validate
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
    }

    private fun validateForm() {
        if (Validate.isFilled(
                binding.textInputEditTextUsername,
                binding.textInputEditTextPassword
            )
        ) viewModel.login()
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
        unloadKoinModules(LoginModule.instance)
        super.onDestroy()
    }

}
