package br.com.jogosusados.features.add.repository

import br.com.jogosusados.features.add.repository.payload.GameAnnouncementPayload
import br.com.jogosusados.features.add.repository.payload.IdWithTitlePayload
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlatformsAPI {

    @GET("platforms")
    suspend fun getPlatforms(): List<IdWithTitlePayload>

    @POST("announcements/game/{idGame}/price/{value}")
    suspend fun saveAnnouncement(
        @Path("idGame") idGame: Long,
        @Path("value") value: String
    ): GameAnnouncementPayload

}
