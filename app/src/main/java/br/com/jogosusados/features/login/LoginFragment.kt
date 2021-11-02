package br.com.jogosusados.features.login

import androidx.navigation.fragment.findNavController
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentLoginBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyvalidation.Validate

class LoginFragment : FragmentMVVMDataBinding<FragmentLoginBinding, LoginViewModel>() {

    override val classViewModel = LoginViewModel::class.java
    override val layout = R.layout.fragment_login

    override fun afterOnCreate() {
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
            "onLoggedIn" -> if (obj != null && obj is String) onLoggedIn()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onLoggedIn() {
        val directions = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

}