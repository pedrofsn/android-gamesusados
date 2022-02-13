package br.com.jogosusados.features.search.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import br.com.jogosusados.features.search.domain.WithID
import br.com.redcode.base.mvvm.databinding.view.BaseViewHolderMVVM

abstract class AdapterPaginated<Data, B : ViewDataBinding>(
    diff: DiffUtil.ItemCallback<Data> = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return (oldItem as? WithID)?.id == (newItem as? WithID)?.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
) : PagedListAdapter<Data, BaseViewHolderMVVM<Data, B>>(diff) {

    abstract val click: ((Data, Int) -> Unit)?
    abstract val layout: Int
    abstract fun getViewHolder(binding: B): BaseViewHolderMVVM<Data, B>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderMVVM<Data, B> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, layout, parent, false)
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolderMVVM<Data, B>, position: Int) {
        getItem(position)?.let { data ->
            holder.bind(
                data = data,
                onClick = click
            )
        }
    }

    fun getItemByPosition(position: Int) = getItem(position)

}
