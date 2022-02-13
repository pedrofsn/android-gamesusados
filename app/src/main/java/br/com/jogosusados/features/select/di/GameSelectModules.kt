package br.com.jogosusados.features.select.di

import br.com.jogosusados.features.select.repository.GameSelectRepositoryImpl
import br.com.jogosusados.features.select.repository.GameSelectAPI
import br.com.jogosusados.features.select.repository.GameSelectRepository
import br.com.jogosusados.features.select.GameSelectViewModel
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object GameSelectModules {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(GameSelectAPI::class.java) }

        factory<GameSelectRepository> { (callback: CallbackNetworkRequest?) ->
            GameSelectRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> GameSelectViewModel(callback) }
    }
}
