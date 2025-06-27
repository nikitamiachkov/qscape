package com.example.kaban2.Screens.RateScreen

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
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RateScreenViewModel : ViewModel()  {

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Loading)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _books = MutableLiveData<List<Chat>>()
    val books: LiveData<List<Chat>> get() = _books

    private var allBooks: List<Chat> = listOf()

    var kolvo:Int = 0;

    init {
        loadUserData()
    }

    fun refreshData() {
        viewModelScope.launch {
            // Здесь вызываем всю нужную логику обновления
            // например: загрузка username, balance, cripto, kolvo и т.д.
            loadUserData()
            //loadCriptoList()
        }
    }

    private fun loadUserData() {

        val userId = supabase.auth.currentUserOrNull()?.id ?: return
        Log.d("SignUp", userId)

        // Загружаем username из profiles
        viewModelScope.launch {

            try {
                allBooks = supabase.from("chat")
                    .select(
                        columns = Columns.list(
                            "user_id",
                            "from",
                            "message"
                        )
                    ) {
                        filter {
                            eq("user_id", userId)
                        }
                    }
                    .decodeList<Chat>()



                _books.value = allBooks
                kolvo = allBooks.size
            } catch (_ex: AuthRestException) {
                //username = "Noy"
                Log.d("SignUp", "lox")
            }
        }
    }

     fun insertUserData(text : String) {

         viewModelScope.launch {

             val userId = supabase.auth.currentUserOrNull()?.id ?: return@launch

             val chat = Chat(userId, true, text)
             supabase.from("chat").insert(chat)
             refreshData()


         }



    }

}