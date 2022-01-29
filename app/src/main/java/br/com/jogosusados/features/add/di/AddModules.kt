package br.com.jogosusados.features.add.di

import br.com.jogosusados.features.add.AddViewModel
import br.com.jogosusados.features.add.repository.PlatformsAPI
import br.com.jogosusados.features.add.repository.AddRepository
import br.com.jogosusados.features.add.repository.AddRepositoryImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object AddModules {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(PlatformsAPI::class.java) }

        factory<AddRepository> { (callback: CallbackNetworkRequest?) ->
            AddRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> AddViewModel(callback) }
    }
}
