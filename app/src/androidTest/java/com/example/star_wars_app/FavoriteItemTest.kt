package com.example.star_wars_app

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.domain.use_case.characters.GetCharacterByUrlUseCase
import com.example.star_wars_app.domain.use_case.characters.SetFavoriteCharacterUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(AndroidJUnit4::class)
class FavoriteItemTest : KoinComponent {

    private val setFavoriteCharacterUseCase: SetFavoriteCharacterUseCase by inject()
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase by inject()

    @Test
    fun setFavoriteCharacter() {

        runBlocking {
            launch(Dispatchers.IO) {
                getCharacterByUrlUseCase("${ApiUrls.characters}1").collectLatest { character ->
                    setFavoriteCharacterUseCase(character).collect { favoriteCharacter ->
                        assert(favoriteCharacter.favorite)
                    }
                }
            }
        }
    }

}