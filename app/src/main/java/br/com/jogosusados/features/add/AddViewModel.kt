package br.com.jogosusados.features.add

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import br.com.concrete.canarinho.formatador.Formatador.VALOR_COM_SIMBOLO
import br.com.jogosusados.features.add.data.LabelAddGame
import br.com.jogosusados.features.add.repository.AddRepository
import br.com.jogosusados.features.search.data.GameItem
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class AddViewModel(callback: CallbackNetworkRequest?) : BaseViewModelWithLiveData<LabelAddGame>(),
    KoinComponent {

    val gameItem = MutableLiveData<GameItem?>()
    val idPlataform = MutableLiveData<Long?>()
    val value = ObservableField<String>()

    private val repository: AddRepository by inject {
        parametersOf(this@AddViewModel, callback)
    }

    override fun load() = process {
        val platforms = repository.getPlatforms()
        LabelAddGame(platforms)
    }

    private fun updatePlatform(idPlatform: Long?) {
        this.idPlataform.postValue(idPlatform)
    }

    fun updateGame(gameItem: GameItem?, changedPlatform: Boolean = false) {
        if (this.gameItem.value == null || gameItem != null || changedPlatform) {
            this.gameItem.postValue(gameItem)
        } else if (this.gameItem.value != null && changedPlatform.not()) {
            this.gameItem.postValue(null)
        }
    }

    fun onPlatformSelected(idPlatform: Long) {
        updatePlatform(idPlatform)
        updateGame(null, true)
    }

    fun validate() {
        val money = value.get()?.trim().orEmpty()
        if (money.isNotBlank()) {
            sendEventToUI("clearErrorValue")
            val moneyUnformatted = VALOR_COM_SIMBOLO.desformata(money)
            gameItem.value?.let { game -> tryToSaveGame(game, moneyUnformatted) }
        } else {
            sendEventToUI("requireValue")
        }
    }

    private fun tryToSaveGame(game: GameItem, money: String) = process("onGameAnnouncementSaved") {
        val result = repository.saveAnnouncement(idGame = game.id, value = money)
        if (result != null) {
            updateGame(null)
            updatePlatform(null)
            value.set("")
        }
        return@process result
    }
}
