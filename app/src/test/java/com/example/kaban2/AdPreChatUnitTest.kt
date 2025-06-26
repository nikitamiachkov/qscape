package com.example.kaban2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kaban2.Domain.models.Chat
import com.example.kaban2.Domain.models.Profile
import com.example.kaban2.Domain.models.Projects
import com.example.kaban2.Screens.MainScreen.MainScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PreRateLogicTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `should count distinct user ids in chat messages`() {
        val testChats = listOf(
            Chat(user_id = "1", from = true, message = "Hi"),
            Chat(user_id = "1", from = false, message = "Reply"),
            Chat(user_id = "2", from = true, message = "Hello"),
            Chat(user_id = "3", from = false, message = "Hey"),
            Chat(user_id = "2", from = true, message = "Another one")
        )

        val distinctUsers = testChats.distinctBy { it.user_id }

        assertEquals(3, distinctUsers.size)
        assertEquals(setOf("1", "2", "3"), distinctUsers.map { it.user_id }.toSet())
    }

    @Test
    fun `should count number of profiles`() {
        val testProfiles = listOf(
            Profile(user_id = "1", username = "Alice", surname = "Smith", dateBirth = "1990-01-01"),
            Profile(user_id = "2", username = "Bob", surname = "Jones", dateBirth = "1991-02-02"),
            Profile(user_id = "3", username = "Charlie", surname = "Brown", dateBirth = "1992-03-03")
        )

        val kolvo2 = testProfiles.size

        assertEquals(3, kolvo2)
    }

    @Test
    fun `should return correct profile usernames`() {
        val testProfiles = listOf(
            Profile(user_id = "1", username = "Anna", surname = "Ivanova", dateBirth = "2000-05-01"),
            Profile(user_id = "2", username = "Boris", surname = "Petrov", dateBirth = "1999-10-10")
        )

        val usernames = testProfiles.map { it.username }

        assertEquals(listOf("Anna", "Boris"), usernames)
    }
}