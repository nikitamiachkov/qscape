package com.example.kaban2.Screens.Admin.AdMainScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kaban2.Domain.Constant.supabase

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

import androidx.lifecycle.viewModelScope

import com.example.kaban2.Domain.models.Profile
import com.example.kaban2.Domain.models.Projects
import com.example.kaban2.Domain.models.Services
import com.example.kaban2.Screens.MainScreen.Project
import io.github.jan.supabase.postgrest.postgrest


import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdMainScreenViewModel : ViewModel() {

    var username by mutableStateOf<String?>(null)
        public set

    private val _books = MutableLiveData<List<Projects>>()
    val books: LiveData<List<Projects>> get() = _books

    private var allBooks: List<Projects> = listOf()

    private val _books2 = MutableLiveData<List<Services>>()
    val books2: LiveData<List<Services>> get() = _books2

    private var allBooks2: List<Services> = listOf()

    private val _books3 = MutableLiveData<List<Profile>>()
    val books3: LiveData<List<Profile>> get() = _books3

    private var allBooks3: List<Profile> = listOf()



    var kolvo:Int = 0;
    var kolvo2:Int = 0;

    init {
        loadUserData()
    }


    private fun loadUserData() {

        val userId = supabase.auth.currentUserOrNull()?.id ?: return
        Log.d("SignUp", userId)

        // Загружаем username из profiles
        viewModelScope.launch {

            try {
                val profileResult = supabase.from("profile")
                    .select(
                        columns = Columns.list(
                            "user_id",
                            "username",
                            "surname",
                            "date_of_birth"
                        )
                    ) {
                        filter {
                            eq("user_id", userId)
                        }
                    }
                    .decodeSingle<Profile>()



                username = profileResult.username
            } catch (_ex: AuthRestException) {
                username = "Noy"
                Log.d("SignUp", "lox")
            }

            try {
                /*allBooks = supabase.from("projects")
                    .select() {
                        filter {
                            eq("user_id", userId)
                        }
                    }
                    .decodeList<Projects>()*/

                allBooks = supabase.postgrest.from("projects").select().decodeList<Projects>()

                _books.value = allBooks

                allBooks2 = supabase.postgrest.from("services").select().decodeList<Services>()
                _books2.value = allBooks2

                allBooks3 = supabase.postgrest.from("profile").select().decodeList<Profile>()
                _books3.value = allBooks3
                kolvo2 = allBooks3.size

                kolvo = allBooks.size



                //username = profileResult
            } catch (_ex: AuthRestException) {
                username = "Noy"
                Log.d("SignUp", "lox")
            }



        }

    }

}