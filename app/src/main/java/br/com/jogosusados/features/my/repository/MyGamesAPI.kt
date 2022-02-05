package br.com.jogosusados.features.my.repository

import br.com.jogosusados.features.add.data.GameAnnouncementPayload
import retrofit2.http.GET

interface MyGamesAPI {

    @GET("announcements/my-games")
    suspend fun getMyGameAnnouncements(): List<GameAnnouncementPayload>

}
