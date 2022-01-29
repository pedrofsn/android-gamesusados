package br.com.jogosusados.features.games.select

import br.com.jogosusados.features.games.GamesAPI
import br.com.jogosusados.features.games.select.repository.GameSelectRepositoryImpl
import br.com.jogosusados.features.games.select.repository.SelectGameRepository
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object GameSelectModules {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(GamesAPI::class.java) }

        factory<SelectGameRepository> { (callback: CallbackNetworkRequest?) ->
            GameSelectRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> GameSelectViewModel(callback) }
    }
}
