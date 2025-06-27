package com.example.kaban2.Screens.Admin.AdRateScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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

class AdRateScreenViewModel (savedStateHandle: SavedStateHandle) : ViewModel()  {

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Loading)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _books = MutableLiveData<List<Chat>>()
    val books: LiveData<List<Chat>> get() = _books

    private var allBooks: List<Chat> = listOf()

    var kolvo:Int = 0;

    var username by mutableStateOf<String?>(null)
        public set

    /*private var _userId: String = supabase.auth.currentUserOrNull()?.id.toString()

    val userId: String
        get() = _userId*/

    init {
        loadUserData()
    }

    val userId = savedStateHandle.get<String>("userId")

    fun setUserId(id: String) {
      //  _userId = id
    }

    private fun loadUserData() {

        //val userId = supabase.auth.currentUserOrNull()?.id ?: return
        Log.d("SignUp", userId.toString())

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
                            userId?.let { eq("user_id", it) }
                        }
                    }
                    .decodeSingle<Profile>()



                username = profileResult.username

                allBooks = supabase.from("chat")
                    .select(
                        columns = Columns.list(
                            "user_id",
                            "from",
                            "message"
                        )
                    ) {
                        filter {
                            userId?.let { eq("user_id", it) }
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

    fun GetName(userId : String?) {
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
                            userId?.let { eq("user_id", it) }
                        }
                    }
                    .decodeSingle<Profile>()



                username = profileResult.username

            } catch (_ex: AuthRestException) {
                //username = "Noy"
                Log.d("SignUp", "lox")
            }
        }
    }

     fun insertUserData(text : String) {

         viewModelScope.launch {

             //val userId = supabase.auth.currentUserOrNull()?.id ?: return@launch

             val chat = Chat(userId, false, text)
             supabase.from("chat").insert(chat)

         }



    }

}