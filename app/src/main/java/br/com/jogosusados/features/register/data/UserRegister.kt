package br.com.jogosusados.features.register.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import br.com.jogosusados.BR
import br.com.jogosusados.features.register.repository.payloads.UserRegisterPayload
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.mvvm.databinding.extensions.watchBR

class UserRegister : BaseObservable() {
    @get:Bindable
    var name: String by watchBR(BR.name)

    @get:Bindable
    var email: String by watchBR(BR.email)

    @get:Bindable
    var phone: String by watchBR(BR.phone)

    @get:Bindable
    var password: String by watchBR(BR.password)

    @get:Bindable
    var passwordConfirmation: String by watchBR(BR.passwordConfirmation)

    fun toPayload() = UserRegisterPayload(
        name = extract safe name,
        phone = extract safe phone,
        email = extract safe email,
        password = extract safe password
    )
}
