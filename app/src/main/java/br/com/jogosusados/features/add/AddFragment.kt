package br.com.jogosusados.features.add

import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentAddBinding
import br.com.redcode.base.extensions.gone
import br.com.redcode.base.extensions.visible
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyvalidation.Validate
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable

class AddFragment : FragmentMVVMDataBinding<FragmentAddBinding, AddViewModel>() {

    override val classViewModel = AddViewModel::class.java
    override val layout = R.layout.fragment_add

    private val observer = observer<LabelAddGame> { updateUI(it) }

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

    private fun addChip(text: String) {
        val context = requireContext()
        val styleChoice = R.style.Widget_MaterialComponents_Chip_Choice
        Chip(context).apply {
            val chipDrawable = ChipDrawable.createFromAttributes(context, null, 0, styleChoice);
            setChipDrawable(chipDrawable)
            this.text = text
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

    private fun clearErrorValue(){
        binding.textInputLayout.error = null
    }
}
