package br.com.jogosusados.features.settings.di

import br.com.jogosusados.features.settings.SettingsViewModel
import br.com.jogosusados.features.settings.repository.SettingsAPI
import br.com.jogosusados.features.settings.repository.SettingsRepository
import br.com.jogosusados.features.settings.repository.SettingsRepositoryImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object SettingsModule {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(SettingsAPI::class.java) }

        factory<SettingsRepository> { (callback: CallbackNetworkRequest?) ->
            SettingsRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> SettingsViewModel(callback) }
    }

}
