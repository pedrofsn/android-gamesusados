package br.com.jogosusados.features.add.repository

import br.com.jogosusados.features.add.data.IdWithTitlePayload
import retrofit2.http.GET

interface PlatformsAPI {

    @GET("platforms")
    suspend fun getPlatforms(): List<IdWithTitlePayload>

}
