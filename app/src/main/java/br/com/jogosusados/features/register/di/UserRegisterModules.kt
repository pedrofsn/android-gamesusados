package br.com.jogosusados.features.register.di

import br.com.jogosusados.features.register.UserRegisterViewModel
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UserRegisterModules {

    val instance = module {

//        single { get<Retrofit>(NetworkModule.NetworkRegular).create(PlatformsAPI::class.java) }
//
//        factory<AddRepository> { (callback: CallbackNetworkRequest?) ->
//            AddRepositoryImpl(
//                api = get(),
//                callbackNetworkRequest = callback
//            )
//        }

        viewModel { (callback: CallbackNetworkRequest?) -> UserRegisterViewModel(callback) }
    }
}
