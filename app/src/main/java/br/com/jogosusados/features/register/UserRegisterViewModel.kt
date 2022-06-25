package br.com.jogosusados.features.register

import br.com.jogosusados.features.register.data.UserRegister
import br.com.jogosusados.features.register.repository.UserRegisterRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class UserRegisterViewModel(callback: CallbackNetworkRequest?) :
    BaseViewModelWithLiveData<UserRegister>(), KoinComponent {

    private val userRegisterRepository: UserRegisterRepository by inject {
        parametersOf(this@UserRegisterViewModel, callback)
    }

    init {
        load()
    }

    override fun load() = liveData.postValue(UserRegister())

    fun tryToSave() = process("onRegistered") {
        val user = liveData.value
        userRegisterRepository.register(user)
    }

}
