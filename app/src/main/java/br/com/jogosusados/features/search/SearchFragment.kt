package br.com.jogosusados.features.search

import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentSearchBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding

class SearchFragment : FragmentMVVMDataBinding<FragmentSearchBinding, SearchViewModel>() {

    override val classViewModel = SearchViewModel::class.java
    override val layout = R.layout.fragment_search

    override fun afterOnCreate() {
        viewModel.load()
    }

}


