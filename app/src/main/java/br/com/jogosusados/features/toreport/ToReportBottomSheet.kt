package br.com.jogosusados.features.toreport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import br.com.jogosusados.BR
import br.com.jogosusados.R
import br.com.jogosusados.databinding.BottomSheetToReportBinding
import br.com.jogosusados.features.announcement.data.Announcement
import br.com.jogosusados.features.search.data.GameItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class ToReportBottomSheet : BottomSheetDialogFragment() {

    private val layout = R.layout.bottom_sheet_to_report
    private lateinit var binding: BottomSheetToReportBinding

    val data by lazy {
        val game = arguments?.getParcelable<GameItem>("game")
        val announcement = arguments?.getParcelable<Announcement>("announcement")
        return@lazy ToReport(game, announcement)
    }

    private val viewModel: ToReportViewModel by viewModel {
        parametersOf(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(ToReportModules.instance)
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.viewModel = viewModel
        defineMVVM(this)
        return binding.root
    }

    private fun defineMVVM(lifecycleOwner: LifecycleOwner) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = lifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparaData()
    }

    private fun preparaData() {
        viewModel.update(data)
        binding.button.setOnClickListener { toReport() }
    }

    private fun toReport() {
        val input = viewModel.input.get().orEmpty()
        val bundle = bundleOf(BUNDLE_INPUT to input, REQUEST_KEY_DATA to data)
        setFragmentResult(REQUEST_KEY_INPUT, bundle)
        findNavController().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(ToReportModules.instance)
    }

    companion object {
        const val REQUEST_KEY_INPUT = "requestKeyInput"
        const val REQUEST_KEY_DATA = "requestKeyData"
        const val BUNDLE_INPUT = "input"
    }
}


