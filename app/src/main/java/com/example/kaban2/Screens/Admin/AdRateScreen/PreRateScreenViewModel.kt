package com.example.kaban2.Screens.Admin.AdRateScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaban2.Domain.Constant.supabase
import com.example.kaban2.Domain.State.ResultState
import com.example.kaban2.Domain.models.Chat
import com.example.kaban2.Domain.models.Profile
import com.example.kaban2.Domain.models.Projects
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PreRateScreenViewModel : ViewModel()  {

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Loading)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _books = MutableLiveData<List<Chat>>()
    val books: LiveData<List<Chat>> get() = _books

    private var allBooks: List<Chat> = listOf()

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
                allBooks = supabase.postgrest.from("chat").select().decodeList<Chat>()

                allBooks = allBooks.distinctBy { it.user_id }

                allBooks3 = supabase.postgrest.from("profile").select().decodeList<Profile>()
                _books3.value = allBooks3
                kolvo2 = allBooks3.size

                _books.value = allBooks
                kolvo = allBooks.size
            } catch (_ex: AuthRestException) {
                //username = "Noy"
                Log.d("SignUp", "lox")
            }
        }
    }

}