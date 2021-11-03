package br.com.jogosusados.features.games

import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData

class GamesViewModel : BaseViewModelWithLiveData<LabelGames>() {

    override fun load() {
        val games = arrayListOf<GameItem>()
        for (i in 1..10) {
            games.add(GameItem(id = i.toLong(), image = "", title = "Fifa 201$i", subtitle = "PS4"))
        }
        liveData.postValue(LabelGames(games))
    }

}