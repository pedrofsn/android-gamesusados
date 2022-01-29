package br.com.jogosusados.domain

import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.getSelectedChip(checkedId : Int) : Chip {
    return children.toList().first { it.id == checkedId }.let { it as Chip }
}
