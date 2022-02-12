package br.com.jogosusados.features.search.repository

import br.com.jogosusados.features.search.repository.payloads.ResponseGETSearchGames
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchGamesAPI {

    @GET("games")
    suspend fun searchGames(
        @Query("page") page: Int,
        @Query("title") title: String?
    ): ResponseGETSearchGames

}
