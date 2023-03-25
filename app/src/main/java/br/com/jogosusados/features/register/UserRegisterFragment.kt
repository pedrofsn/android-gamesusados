package br.com.jogosusados.features.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentUserRegisterBinding
import br.com.jogosusados.features.register.di.UserRegisterModules
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.base.utils.Alerts
import br.com.redcode.easymask.handlePhone
import br.com.redcode.easyvalidation.extensions.isFilled
import br.com.redcode.easyvalidation.extensions.setMessageError
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class UserRegisterFragment :
    FragmentMVVMDataBinding<FragmentUserRegisterBinding, UserRegisterViewModel>() {

    override val classViewModel = UserRegisterViewModel::class.java
    override val layout: Int = R.layout.fragment_user_register

    private val userRegisterViewModel: UserRegisterViewModel by viewModel {
        parametersOf(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(UserRegisterModules.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = userRegisterViewModel
        defineMVVM(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        binding.textInputEditTextPhone.handlePhone()
    }

    private fun setupToolbar() {
        binding.toolbar.setTitle(R.string.register)
        binding.toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.toolbar.inflateMenu(R.menu.user_register_menu)
        binding.toolbar.setOnMenuItemClickListener { item: MenuItem ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.save -> {
                    tryToSaveForm()
                    true
                }
                else -> false
            }
        }
    }

    private fun tryToSaveForm() {
        if (isFilled(
                binding.textInputEditTextFullname,
                binding.textInputEditTextPhone,
                binding.textInputEditTextEmail,
                binding.textInputEditTextPassword,
                binding.textInputEditTextPasswordConfirmation,
            )
        ) {
            viewModel.tryToSave()
        }
    }

    private fun isFilled(vararg inputs: TextInputEditText): Boolean {
        var result = true
        inputs.forEach {
            if (it.isFilled()) {
                it.setMessageError(null)
            } else {
                result = false
            }
        }
        return result
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onRegistered" -> onRegistered()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onRegistered() {
        Alerts.showDialogOk(
            context = requireActivity(),
            title = getString(R.string.register_success_title),
            mensagem = getString(R.string.register_success)
        ) {
            val directions = UserRegisterFragmentDirections.actionUserRegisterFragmentToHomeFragment()
            findNavController().navigate(directions, navOptions { popUpTo })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(UserRegisterModules.instance)
    }
}

