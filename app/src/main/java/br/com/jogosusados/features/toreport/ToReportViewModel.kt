package br.com.jogosusados.features.toreport

import androidx.databinding.ObservableField
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class ToReportViewModel(callback: CallbackNetworkRequest?) : BaseViewModelWithLiveData<ToReport>(),
    KoinComponent {

    val input = ObservableField<String>()
    val title = ObservableField<String>()

    override fun load() {
        liveData.value?.let {
            println(it.toString())
        }
    }

    fun update(data: ToReport) {
        val title = get<String>(named(data.type)) { parametersOf(data.getTitle().toString()) }
        this@ToReportViewModel.title.set(title)
        liveData.value = data
    }

    fun toReport() {
        toast(input.get().toString())
    }
}
