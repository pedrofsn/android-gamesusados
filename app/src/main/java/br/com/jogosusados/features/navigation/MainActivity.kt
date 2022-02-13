package br.com.jogosusados.features.navigation

import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import br.com.jogosusados.R
import br.com.redcode.easyrestful.library.domain.BaseActivityRestful

class MainActivity(override val layout: Int = R.layout.activity_main) : BaseActivityRestful() {

    val navController by lazy { Navigation.findNavController(this, R.id.navHost) }

    override fun afterOnCreate() {
        //NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null) || super.onSupportNavigateUp()
    }

    override fun clearLocalDataAndGoToLoginScreen() {
        toast("limpar os dados locais e rotear par ao login")
        navController.navigate(R.id.loginFragment, null, navOptions { popUpTo })
    }
}
