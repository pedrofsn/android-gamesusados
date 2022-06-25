package br.com.jogosusados.features.register

import br.com.jogosusados.features.register.data.UserRegister
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent

class UserRegisterViewModel(callback: CallbackNetworkRequest?) :
    BaseViewModelWithLiveData<UserRegister>(), KoinComponent {

    init {
        load()
    }

    override fun load() = liveData.postValue(UserRegister())

    fun tryToSave() {
        liveData.value?.let {
            showSimpleAlert(it.toString())
        }
    }

}
