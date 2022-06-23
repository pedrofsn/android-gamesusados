package br.com.jogosusados.features.my.repository

import br.com.jogosusados.features.add.repository.payload.GameAnnouncementPayload
import br.com.jogosusados.network.CustomErrorPayload
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyGamesAPI {

    @GET("announcements/my-games")
    suspend fun getMyGameAnnouncements(): List<GameAnnouncementPayload>

    @POST("announcements/{idGameAnnouncement}/toggle/false")
    suspend fun disable(@Path("idGameAnnouncement") idGameAnnouncement: Long): CustomErrorPayload

}
