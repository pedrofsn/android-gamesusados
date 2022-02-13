package br.com.jogosusados.features.select

import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.features.select.repository.GameSelectRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class GameSelectViewModel(callback: CallbackNetworkRequest?) :
    BaseViewModelWithLiveData<List<GameItem>>(), KoinComponent {

    private val gameSelectRepository: GameSelectRepository by inject {
        parametersOf(this@GameSelectViewModel, callback)
    }

    override fun load() = process {
        gameSelectRepository.getGames(idPlatform = id)
    }

}
