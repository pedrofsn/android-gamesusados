package br.com.jogosusados.features.settings.repository.payload

import android.content.Context
import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.data.UserType
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload
import org.koin.core.context.GlobalContext

data class ProfileDTO(
    val name: String?,
    val phone: String?,
    val image: String?,
    val email: String?,
    val type: String?
) : Payload<Profile> {
    override fun toModel() = Profile(
        name = extract safe name,
        phone = extract safe phone,
        email = extract safe email,
        type = getUserType(),
        image = image
    )

    private fun getUserType(): String {
        val userType = UserType.getUserType(extract safe type)
        val context = GlobalContext.get().get<Context>()
        return context.getString(userType.name)
    }
}
