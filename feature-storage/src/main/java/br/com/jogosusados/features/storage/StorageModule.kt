package br.com.jogosusados.features.storage

import org.koin.dsl.module

object StorageModule {

    private const val FILE_NAME = "my_shared_preferences_games_usados"

    val instace = module {
        single<Storage> { StorageImpl(fileName = FILE_NAME, context = get()) }
    }
}
