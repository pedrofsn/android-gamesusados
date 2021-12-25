package br.com.jogosusados.features.add

import br.com.jogosusados.features.add.repository.AddRepository
import br.com.jogosusados.features.games.GameItem
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class AddViewModel(callback: CallbackNetworkRequest?) : BaseViewModelWithLiveData<LabelAddGame>(),
    KoinComponent {

    private val repository: AddRepository by inject {
        parametersOf(
            this@AddViewModel,
            callback
        )
    }

    override fun load() {
        process {
            val platforms = repository.getPlatforms()
            LabelAddGame(platforms)
        }

        val element = GameItem(
            id = 15.toLong(),
            image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSIee3Eac7z5dBDmR6d_pCvnDdU9xhjpv9oTTW7ladJKGD3xTJjYpFK1i6x84I8dJ4uxir2YdTBYwkAgJP3-9c6fi6dGeD6O7Oy-835i72HaeMXMIBEG9Ks8w&usqp=CAE",
            title = "Ghost of Tsushima",
            subtitle = "PS4"
        )
    }

    var index = 0
    fun validate() {
        index += 1
        if (index % 2 == 0) {
            sendEventToUI("requireValue")
        } else {
            sendEventToUI("clearErrorValue")
        }
    }
}
