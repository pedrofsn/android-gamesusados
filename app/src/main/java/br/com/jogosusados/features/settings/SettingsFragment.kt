package br.com.jogosusados.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentSettingsBinding
import br.com.jogosusados.features.settings.di.SettingsModule
import br.com.jogosusados.features.settings.data.Profile
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class SettingsFragment : FragmentMVVMDataBinding<FragmentSettingsBinding, SettingsViewModel>() {

    override val classViewModel = SettingsViewModel::class.java
    override val layout = R.layout.fragment_settings

    private val settingsViewModel: SettingsViewModel by viewModel {
        parametersOf(this@SettingsFragment.requireActivity())
    }

    private val observer = observer<Profile> { updateUI(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(SettingsModule.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = settingsViewModel
        defineMVVM(this)
        return binding.root
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
    }

    private fun updateUI(label: Profile) {
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(SettingsModule.instance)
    }

}
