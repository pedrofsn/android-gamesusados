package br.com.jogosusados.features.games.select

import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.features.games.select.repository.SelectGameRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class GameSelectViewModel(callback: CallbackNetworkRequest?) :
    BaseViewModelWithLiveData<List<GameItem>>(), KoinComponent {

    private val selectGameRepository: SelectGameRepository by inject {
        parametersOf(this@GameSelectViewModel, callback)
    }

    override fun load() = process {
        selectGameRepository.getGames(idPlatform = id)
    }

}
