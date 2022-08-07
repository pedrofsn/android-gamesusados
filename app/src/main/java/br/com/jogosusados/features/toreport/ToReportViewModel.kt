package br.com.jogosusados.features.toreport

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class ToReportViewModel : ViewModel(), KoinComponent {

    val liveData = MutableLiveData<ToReport>()

    val input = ObservableField<String>()
    val title = ObservableField<String>()

    fun update(data: ToReport) {
        val title = get<String>(named(data.type)) { parametersOf(data.getTitle().toString()) }
        this@ToReportViewModel.title.set(title)
        liveData.value = data
    }
}
