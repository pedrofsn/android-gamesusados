package br.com.jogosusados.features.my

import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentMyBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding

class MyFragment : FragmentMVVMDataBinding<FragmentMyBinding, MyViewModel>() {

    override val classViewModel = MyViewModel::class.java
    override val layout = R.layout.fragment_my

    override fun afterOnCreate() {

    }

}
