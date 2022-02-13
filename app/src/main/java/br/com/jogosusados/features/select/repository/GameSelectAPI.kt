package br.com.jogosusados.features.select.repository

import br.com.jogosusados.features.search.repository.payloads.GameDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GameSelectAPI {

    @GET("games/platform/{idPlatform}")
    suspend fun getGames(@Path("idPlatform") idPlatform: Long): List<GameDTO>

}
