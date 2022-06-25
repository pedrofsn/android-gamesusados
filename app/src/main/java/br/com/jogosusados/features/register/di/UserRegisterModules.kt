package br.com.jogosusados.features.register.di

import br.com.jogosusados.features.register.UserRegisterViewModel
import br.com.jogosusados.features.register.repository.UserRegisterAPI
import br.com.jogosusados.features.register.repository.UserRegisterRepository
import br.com.jogosusados.features.register.repository.UserRegisterRepositoryImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object UserRegisterModules {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(UserRegisterAPI::class.java) }

        factory<UserRegisterRepository> { (callback: CallbackNetworkRequest?) ->
            UserRegisterRepositoryImpl(
                api = get(),
                storage = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> UserRegisterViewModel(callback) }
    }
}
