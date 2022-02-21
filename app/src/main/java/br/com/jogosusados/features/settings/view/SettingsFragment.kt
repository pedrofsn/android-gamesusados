package br.com.jogosusados.features.settings.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentSettingsBinding
import br.com.jogosusados.features.settings.SettingsViewModel
import br.com.jogosusados.features.settings.data.ImageUploaded
import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.di.SettingsModule
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyglide.library.load
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File

class SettingsFragment : FragmentMVVMDataBinding<FragmentSettingsBinding, SettingsViewModel>(),
    EasyImage.Callbacks {

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

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onUploaded" -> if (obj != null && obj is ImageUploaded) onUploaded(obj)
            "chooseImage" -> chooseImage()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onUploaded(imageUploaded: ImageUploaded) {
        binding.imageView.load(imageUploaded.url)
        hideProgress()
    }

    private fun chooseImage() {
        EasyImage.openChooserWithGallery(this, getString(R.string.select_image), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            /*resultCode == Activity.RESULT_OK && data != null -> {
                data.data?.let { uri -> viewModel.uploadProfileImage(uri) }
            }*/
            else -> {
                EasyImage.handleActivityResult(
                    requestCode,
                    resultCode,
                    data,
                    requireActivity(),
                    this
                )
            }
        }
    }

    override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {

    }

    override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {

    }

    override fun onImagesPicked(
        files: MutableList<File>,
        source: EasyImage.ImageSource?,
        type: Int
    ) {
        files.firstOrNull()?.let { file -> viewModel.uploadProfileImage(file) }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(SettingsModule.instance)
    }
}
