package br.com.jogosusados.features.settings

import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentSettingsBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding

class SettingsFragment : FragmentMVVMDataBinding<FragmentSettingsBinding, SettingsViewModel>() {

    override val classViewModel = SettingsViewModel::class.java
    override val layout = R.layout.fragment_settings

    override fun afterOnCreate() {

    }

}
