package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.features.announcement.repository.payload.ResponseGETGameAnnouncement
import retrofit2.http.GET
import retrofit2.http.Path

interface GameAnnouncementsAPI {

    @GET("announcements/game/{id}")
    suspend fun getGameAnnouncements(@Path("id") id: Long): ResponseGETGameAnnouncement

}
