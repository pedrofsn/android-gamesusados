package br.com.jogosusados.features.navigation

import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import br.com.jogosusados.R
import br.com.jogosusados.features.storage.Storage
import br.com.jogosusados.network.NetworkAndErrorHandler.Companion.HTTP_STATUS_CODE_UNAUTHORIZED_ACCESS
import br.com.redcode.easyrestful.library.domain.BaseActivityRestful
import org.koin.android.ext.android.inject

class MainActivity(override val layout: Int = R.layout.activity_main) : BaseActivityRestful() {

    val navController by lazy { Navigation.findNavController(this, R.id.navHost) }
    private val storage by inject<Storage>()

    override fun afterOnCreate() {
        //NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null) || super.onSupportNavigateUp()
    }

    override fun handleActionAPI(action: Int, id: String) {
        if (HTTP_STATUS_CODE_UNAUTHORIZED_ACCESS == action) {
            showSimpleAlert(R.string.network_unauthorized_access)
        } else {
            super.handleActionAPI(action, id)
        }
    }

    override fun clearLocalDataAndGoToLoginScreen() {
        storage.clearAll()
        navController.navigate(R.id.loginFragment, null, navOptions { popUpTo })
    }
}
