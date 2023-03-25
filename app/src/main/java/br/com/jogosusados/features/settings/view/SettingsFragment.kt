package br.com.jogosusados.features.settings.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentSettingsBinding
import br.com.jogosusados.features.home.view.HomeFragment
import br.com.jogosusados.features.navigation.MainActivity
import br.com.jogosusados.features.settings.SettingsViewModel
import br.com.jogosusados.features.settings.data.ImageUploaded
import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.di.SettingsModule
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.base.utils.Alerts
import br.com.redcode.easyglide.library.loadWithCircleTransform
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource

class SettingsFragment : FragmentMVVMDataBinding<FragmentSettingsBinding, SettingsViewModel>() {

    override val classViewModel = SettingsViewModel::class.java
    override val layout = R.layout.fragment_settings

    private val settingsViewModel: SettingsViewModel by viewModel {
        parametersOf(this@SettingsFragment.requireActivity())
    }

    private val observer = observer<Profile> { updateUI(it) }

    private val easyImage: EasyImage by lazy {
        EasyImage.Builder(requireActivity())
            .setChooserTitle(getString(R.string.select_image))
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .allowMultiple(false)
            .build()
    }

    private val drawable by lazy {
        ContextCompat.getDrawable(requireActivity(), R.drawable.ic_baseline_person_24)
    }

    private val cameraCallback by lazy {
        object : DefaultCallback() {
            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                imageFiles.firstOrNull()?.let { image ->
                    viewModel.uploadProfileImage(image.file)
                }
            }

            override fun onImagePickerError(
                @NonNull error: Throwable,
                @NonNull source: MediaSource
            ) = error.printStackTrace()
        }
    }

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
        setupToolbar()
        return binding.root
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        getHomeFragmentToolbar()?.apply {
            inflateMenu(R.menu.settins_menu)
            setOnMenuItemClickListener { item: MenuItem ->
                return@setOnMenuItemClickListener when (item.itemId) {
                    R.id.logout -> {
                        onLogout()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun getHomeFragmentToolbar(): Toolbar? {
        val homeFragment = parentFragment?.parentFragment
        return (homeFragment as? HomeFragment)?.binding?.toolbar
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
        if (label.image.isNullOrBlank()) {
            binding.imageView.setImageDrawable(drawable)
        } else {
            showImage(label.image)
        }

        hideProgress()
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onUploaded" -> if (obj != null && obj is ImageUploaded) onUploaded(obj)
            "chooseImage" -> chooseImage()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onLogout() {
        Alerts.showDialogYesOrNot(requireActivity(), getString(R.string.logout_confirmation)) {
            (requireActivity() as MainActivity).clearLocalDataAndGoToLoginScreen()
        }
    }

    private fun onUploaded(imageUploaded: ImageUploaded) {
        showImage(imageUploaded.url)
        hideProgress()
    }

    private fun showImage(url: String?) {
        binding.imageView.loadWithCircleTransform(
            url = url,
            diskStrategy = DiskCacheStrategy.NONE,
            enableCrossfade = true
        )
    }

    private fun chooseImage() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            easyImage.openCameraForImage(this)
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_WRITE_PERMISSION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        easyImage.handleActivityResult(
            requestCode,
            resultCode,
            data,
            requireActivity(),
            cameraCallback
        )
    }

    override fun onDestroy() {
        getHomeFragmentToolbar()?.menu?.clear()
        super.onDestroy()
        unloadKoinModules(SettingsModule.instance)
    }

    companion object {
        private const val REQUEST_WRITE_PERMISSION = 123
    }
}
