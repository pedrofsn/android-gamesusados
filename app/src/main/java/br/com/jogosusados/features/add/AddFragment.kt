package br.com.jogosusados.features.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentAddBinding
import br.com.jogosusados.features.add.data.IdWithTitle
import br.com.jogosusados.features.add.di.AddModules
import br.com.redcode.base.extensions.gone
import br.com.redcode.base.extensions.visible
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyvalidation.Validate
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class AddFragment : FragmentMVVMDataBinding<FragmentAddBinding, AddViewModel>() {

    override val classViewModel = AddViewModel::class.java
    override val layout = R.layout.fragment_add

    private val addViewModel: AddViewModel by viewModel {
        parametersOf(this@AddFragment.requireActivity())
    }

    private val observer = observer<LabelAddGame> { updateUI(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(AddModules.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = addViewModel
        defineMVVM(this)
        return binding.root
    }


    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.textViewAdd.visible()
        }

        binding.textViewAdd.setOnClickListener {
            binding.textViewAdd.gone()

            binding.materialCardView.visible()
            binding.textInputLayout.visible()
            binding.button.visible()
        }

        viewModel.load()
    }

    private fun updateUI(labelAddGame: LabelAddGame) {
        binding.chipGroup.removeAllViews()
        labelAddGame.platforms.forEach { addChip(it) }
        binding.imageView.load(labelAddGame.game?.image)
    }

    private fun addChip(idWithTitle: IdWithTitle) {
        val context = requireContext()
        val styleChoice = R.style.Widget_MaterialComponents_Chip_Choice
        Chip(context).apply {
            val chipDrawable = ChipDrawable.createFromAttributes(context, null, 0, styleChoice);
            setChipDrawable(chipDrawable)
            this.text = idWithTitle.title
            this.tag = idWithTitle.id
            binding.chipGroup.addView(this)
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "requireValue" -> requireValue()
            "clearErrorValue" -> clearErrorValue()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun requireValue() {
        Validate.isFilled(binding.textInputEditText)
    }

    private fun clearErrorValue() {
        binding.textInputLayout.error = null
    }

    override fun onDestroy() {
        unloadKoinModules(AddModules.instance)
        super.onDestroy()
    }
}
