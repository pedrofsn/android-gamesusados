package br.com.jogosusados.features.home.view

import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentHomeBinding
import br.com.jogosusados.features.home.HomeViewModel
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding

class HomeFragment : FragmentMVVMDataBinding<FragmentHomeBinding, HomeViewModel>() {

    override val classViewModel = HomeViewModel::class.java
    override val layout = R.layout.fragment_home

    override fun afterOnCreate() {
        super.afterOnCreate()
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
        avoidReselection()
    }

    private fun avoidReselection() {
        binding.bottomNavigationView.setOnItemReselectedListener { }
    }

}
