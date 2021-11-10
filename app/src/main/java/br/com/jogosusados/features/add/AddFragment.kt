package br.com.jogosusados.features.add

import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentAddBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable

class AddFragment : FragmentMVVMDataBinding<FragmentAddBinding, AddViewModel>() {

    override val classViewModel = AddViewModel::class.java
    override val layout = R.layout.fragment_add

    override fun afterOnCreate() {
        addChip("Playstation 5")
        addChip("Xbox One")
        addChip("Switch")
        addChip("3DS")
        addChip("PSP")
        addChip("N64")
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId -> toast(checkedId.toString()) }
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

}
