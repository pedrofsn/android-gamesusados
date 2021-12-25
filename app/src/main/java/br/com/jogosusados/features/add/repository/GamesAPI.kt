package br.com.jogosusados.features.add.repository

import br.com.jogosusados.features.add.data.IdWithTitlePayload
import retrofit2.http.GET

interface GamesAPI {

    @GET("platforms")
    suspend fun getPlatforms(): List<IdWithTitlePayload>

}
