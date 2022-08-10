package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.features.announcement.repository.payload.ToReportPayload
import br.com.jogosusados.features.toreport.ToReport
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.base.models.ErrorAPI
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled

class ToReportRepositoryImpl(
    private val api: ToReportAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : ToReportRepository {

    override suspend fun toReport(reportData: ToReport, input: String): ErrorAPI {
        val payload = ToReportPayload(input, reportData.getId(), reportData.type.type)
        val request = Request with api call { toReport(payload) }
        val result: ErrorHandled? = request handled callbackNetworkRequest
        val errorAPI = result?.message?.takeIf { it.isNotBlank() }?.let { ErrorAPI(msg = it) }
        return errorAPI ?: ErrorAPI()
    }
}
