package br.com.jogosusados.features.search

import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.features.games.list.LabelGames
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData

class SearchViewModel : BaseViewModelWithLiveData<LabelGames>() {

    override fun load() {
        val games = arrayListOf<GameItem>()
        for (i in 1..10) {
            games.add(
                GameItem(
                    id = i.toLong(),
                    image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSIee3Eac7z5dBDmR6d_pCvnDdU9xhjpv9oTTW7ladJKGD3xTJjYpFK1i6x84I8dJ4uxir2YdTBYwkAgJP3-9c6fi6dGeD6O7Oy-835i72HaeMXMIBEG9Ks8w&usqp=CAE",
                    title = "Ghost of Tsushima",
                    subtitle = "PS4"
                )
            )
        }
        liveData.postValue(LabelGames(games))
    }

}
