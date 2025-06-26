package com.example.kaban2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kaban2.Domain.models.Chat
import com.example.kaban2.Domain.models.Projects
import com.example.kaban2.Screens.MainScreen.MainScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ChatFilterTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `filterChats should return chats containing a keyword`() {
        val testChats = listOf(
            Chat(user_id = "1", from = true, message = "Hello!"),
            Chat(user_id = "1", from = false, message = "How are you?"),
            Chat(user_id = "1", from = true, message = "See you tomorrow."),
            Chat(user_id = "1", from = false, message = "HELLO again!")
        )

        // Фильтруем по ключевому слову "hello" (регистр не важен)
        val filtered = testChats.filter { it.message!!.contains("hello", ignoreCase = true) }

        assertEquals(2, filtered.size)
        assertEquals(listOf("Hello!", "HELLO again!"), filtered.map { it.message })
    }

    @Test
    fun `filterChats should return empty list if no messages match`() {
        val testChats = listOf(
            Chat(user_id = "1", from = true, message = "Goodbye"),
            Chat(user_id = "1", from = false, message = "See you later")
        )

        val filtered = testChats.filter { it.message!!.contains("hello", ignoreCase = true) }

        assertEquals(0, filtered.size)
    }

    @Test
    fun `filterChats should return only messages from user`() {
        val testChats = listOf(
            Chat(user_id = "1", from = true, message = "My message"),
            Chat(user_id = "1", from = false, message = "Other person's message")
        )

        val userMessages = testChats.filter { it.from }

        assertEquals(1, userMessages.size)
        assertEquals("My message", userMessages.first().message)
    }
}