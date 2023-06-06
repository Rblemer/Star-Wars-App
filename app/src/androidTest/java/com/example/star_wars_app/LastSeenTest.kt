package com.example.star_wars_app

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.star_wars_app.commom.util.date.DateUtils
import com.example.star_wars_app.data.mapper.characters.charactersEntity
import com.example.star_wars_app.data.mapper.characters.toCharacter
import com.example.star_wars_app.domain.use_case.characters.SetCharacterLastSeenUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(AndroidJUnit4::class)
class LastSeenTest : KoinComponent {

    private val setCharacterLastSeenUseCase: SetCharacterLastSeenUseCase by inject()

    @Test
    fun setCharacterLastSeen() {
        val character = charactersEntity.first()
        runBlocking {
            launch(Dispatchers.IO) {
                setCharacterLastSeenUseCase(character.toCharacter()).collectLatest {
                    assert(it.lastSeen == DateUtils.getCurrentDate())
                }
            }
        }
    }

}