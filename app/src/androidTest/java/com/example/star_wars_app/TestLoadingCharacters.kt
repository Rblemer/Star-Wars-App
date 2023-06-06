package com.example.star_wars_app

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.domain.model.Character
import com.example.star_wars_app.domain.use_case.characters.GetCharactersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(AndroidJUnit4::class)
class TestLoadingCharacters : KoinComponent {

    private val getCharactersUseCase: GetCharactersUseCase by inject()

    @Test
    fun getAllCharacters() {
        val totalCount = 82
        runBlocking {
            val characters = mutableListOf<Character>()
            getCharactersUseCase(ApiUrls.characters, clearLocalDataSource = false).collect { firstPage ->
                characters.addAll(firstPage.results)
                var nextPage = firstPage.next
                while(nextPage != null) {
                    getCharactersUseCase(nextPage).collect {
                        characters.addAll(firstPage.results)
                        nextPage = it.next
                    }
                }
                assert(characters.size == totalCount)
            }
        }
    }

}