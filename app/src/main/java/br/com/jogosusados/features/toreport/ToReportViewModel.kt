package br.com.jogosusados.features.toreport

import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData

class ToReportViewModel(callback: CallbackNetworkRequest?) : BaseViewModelWithLiveData<ToReport>() {

    override fun load() {
        liveData.value?.let {
            println(it.toString())
        }
    }

    fun update(data: ToReport) {
        liveData.value = data
    }
}
