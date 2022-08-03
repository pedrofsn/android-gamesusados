package br.com.jogosusados.features.toreport

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ToReportModules {

    val instance = module {

//        single { get<Retrofit>(NetworkModule.NetworkRegular).create(GameAnnouncementsAPI::class.java) }
//
//        factory<GameAnnouncementRepository> { (callback: CallbackNetworkRequest?) ->
//            GameAnnouncementRepositoryImpl(
//                api = get(),
//                callbackNetworkRequest = callback
//            )
//        }

        viewModel { (callback: CallbackNetworkRequest?) -> ToReportViewModel(callback) }
    }
}
