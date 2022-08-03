package br.com.jogosusados.features.toreport

import androidx.databinding.ObservableField
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData

class ToReportViewModel(callback: CallbackNetworkRequest?) : BaseViewModelWithLiveData<ToReport>() {

    val input = ObservableField<String>()

    override fun load() {
        liveData.value?.let {
            println(it.toString())
        }
    }

    fun update(data: ToReport) {
        liveData.value = data
    }
}
