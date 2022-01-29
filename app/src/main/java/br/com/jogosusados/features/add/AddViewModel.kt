package br.com.jogosusados.features.add

import androidx.lifecycle.MutableLiveData
import br.com.jogosusados.features.add.repository.AddRepository
import br.com.jogosusados.features.games.list.GameItem
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class AddViewModel(callback: CallbackNetworkRequest?) : BaseViewModelWithLiveData<LabelAddGame>(),
    KoinComponent {

    val gameItem = MutableLiveData<GameItem>()
    val idPlataform = MutableLiveData<Long>()

    private val repository: AddRepository by inject {
        parametersOf(this@AddViewModel, callback)
    }

    override fun load() = process {
        val platforms = repository.getPlatforms()
        LabelAddGame(platforms)
    }

    fun updateGame(gameItem: GameItem?) = this.gameItem.postValue(gameItem)

    var index = 0
    fun validate() {
        index += 1
        if (index % 2 == 0) {
            sendEventToUI("requireValue")
        } else {
            sendEventToUI("clearErrorValue")
        }
    }

    fun onPlatformSelected(idPlatform: Long) {
        idPlataform.postValue(idPlatform)
        updateGame(null)
    }
}
