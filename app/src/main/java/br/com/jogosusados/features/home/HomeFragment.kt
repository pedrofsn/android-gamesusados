package br.com.jogosusados.features.home

import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentHomeBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding

class HomeFragment : FragmentMVVMDataBinding<FragmentHomeBinding, HomeViewModel>() {

    override val classViewModel = HomeViewModel::class.java
    override val layout = R.layout.fragment_home

    override fun afterOnCreate() {
        super.afterOnCreate()
    }

}
