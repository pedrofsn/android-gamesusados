package br.com.jogosusados.features.login.di

import br.com.jogosusados.features.login.LoginViewModel
import br.com.jogosusados.features.login.repository.LoginAPI
import br.com.jogosusados.features.login.repository.LoginRepository
import br.com.jogosusados.features.login.repository.LoginRepositoryImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object LoginModule {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(LoginAPI::class.java) }

        factory<LoginRepository> { (callback: CallbackNetworkRequest?) ->
            LoginRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> LoginViewModel(callback) }
    }
}
