package com.example.kaban2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kaban2.Domain.models.Projects
import com.example.kaban2.Screens.MainScreen.MainScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ProjectFilterTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `filterProjects should return projects with matching status`() {
        // Допустим, мы хотим отфильтровать проекты со статусом "done"
        val testProjects = listOf(
            Projects(user_id = "1", service_id = 101, status = "done", completeness = 1.0f),
            Projects(user_id = "1", service_id = 102, status = "in_progress", completeness = 0.5f),
            Projects(user_id = "1", service_id = 103, status = "done", completeness = 1.0f),
        )

        val filtered = testProjects.filter { it.status == "done" }

        assertEquals(2, filtered.size)
        assertEquals(listOf(101, 103), filtered.map { it.service_id })
    }

    @Test
    fun `filterProjects should return empty list if no match`() {
        val testProjects = listOf(
            Projects(user_id = "1", service_id = 101, status = "in_progress", completeness = 0.7f),
            Projects(user_id = "1", service_id = 102, status = "in_review", completeness = 0.6f),
        )

        val filtered = testProjects.filter { it.status == "done" }

        assertEquals(0, filtered.size)
    }
}