package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.features.announcement.repository.payload.ToReportPayload
import br.com.jogosusados.network.CustomErrorPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface ToReportAPI {

    @POST("report")
    suspend fun toReport(@Body body: ToReportPayload): CustomErrorPayload

}
