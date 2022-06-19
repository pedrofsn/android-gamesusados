package br.com.jogosusados.features.settings.data

import androidx.annotation.StringRes
import br.com.jogosusados.R

sealed class UserType(val typeName: String, @StringRes val name: Int) {
    object Admin : UserType("ADMIN", R.string.usertype_admin)
    object Manager : UserType("MANAGER", R.string.usertype_manager)
    object Regular : UserType("USER", R.string.usertype_regular)

    companion object {

        fun getUserType(typeName: String?) = when (typeName) {
            Admin.typeName -> Admin
            Manager.typeName -> Manager
            else -> Regular
        }
    }
}
