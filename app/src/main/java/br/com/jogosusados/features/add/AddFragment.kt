package br.com.jogosusados.features.add

import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentAddBinding
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding

class AddFragment : FragmentMVVMDataBinding<FragmentAddBinding, AddViewModel>() {

    override val classViewModel = AddViewModel::class.java
    override val layout = R.layout.fragment_add

    override fun afterOnCreate() {

    }

}
