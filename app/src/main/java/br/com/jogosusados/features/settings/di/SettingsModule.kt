package br.com.jogosusados.features.settings.di

import br.com.jogosusados.features.settings.SettingsViewModel
import br.com.jogosusados.features.settings.repository.SettingsAPI
import br.com.jogosusados.features.settings.repository.SettingsRepository
import br.com.jogosusados.features.settings.repository.SettingsRepositoryImpl
import br.com.jogosusados.features.settings.repository.interactor.SettingsLocalInteractor
import br.com.jogosusados.features.settings.repository.interactor.SettingsLocalInteractorImpl
import br.com.jogosusados.features.settings.repository.interactor.SettingsRemoteInteractor
import br.com.jogosusados.features.settings.repository.interactor.SettingsRemoteInteractorImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

object SettingsModule {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(SettingsAPI::class.java) }

        factory<SettingsLocalInteractor> {
            SettingsLocalInteractorImpl(storage = get(), moshi = get())
        }

        factory<SettingsRemoteInteractor> { (callback: CallbackNetworkRequest?) ->
            SettingsRemoteInteractorImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        factory<SettingsRepository> { (callback: CallbackNetworkRequest?) ->
            SettingsRepositoryImpl(
                local = get(),
                remote = get { parametersOf(callback) },
                context = get()
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> SettingsViewModel(callback) }
    }

}
