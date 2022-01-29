package br.com.jogosusados.features.games

import br.com.jogosusados.features.games.domain.payload.GameDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GamesAPI {

    @GET("games/platform/{idPlatform}")
    suspend fun getGames(@Path("idPlatform") idPlatform: Long): List<GameDTO>

}
