package br.com.jogosusados.features.add

import br.com.jogosusados.features.games.GameItem
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData

class AddViewModel : BaseViewModelWithLiveData<LabelAddGame>() {

    override fun load() {
        val element = GameItem(
            id = 15.toLong(),
            image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSIee3Eac7z5dBDmR6d_pCvnDdU9xhjpv9oTTW7ladJKGD3xTJjYpFK1i6x84I8dJ4uxir2YdTBYwkAgJP3-9c6fi6dGeD6O7Oy-835i72HaeMXMIBEG9Ks8w&usqp=CAE",
            title = "Ghost of Tsushima",
            subtitle = "PS4"
        )
        val platforms = listOf(
            "Playstation 5",
            "Xbox One",
            "Switch",
            "3DS",
            "PSP",
            "N64",
        )
        val label = LabelAddGame(platforms, game = element, value = "")
        liveData.postValue(label)
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
