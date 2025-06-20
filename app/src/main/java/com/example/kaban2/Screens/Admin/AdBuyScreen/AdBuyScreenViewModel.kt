package com.example.kaban2.Screens.Admin.AdBuyScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaban2.Domain.Constant.supabase
import com.example.kaban2.Domain.State.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import com.example.kaban2.Domain.models.Services

class AdBuyScreenViewModel : ViewModel() {

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Loading)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _books = MutableLiveData<List<Services>>()
    val books: LiveData<List<Services>> get() = _books

    var kolvo:Int = 0;

    private var allBooks: List<Services> = listOf()

    init {
        loadBooks()
    }

    private fun loadBooks() {
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            try {
                allBooks = supabase.postgrest.from("services").select().decodeList<Services>()
                _books.value = allBooks
                kolvo = allBooks.size
                Log.d("MainBooks", "Successss")
                Log.d("MainBooks", allBooks.toString())

                _resultState.value = ResultState.Success("Success")
            } catch (_ex: AuthRestException) {
                Log.d("MainBooks", _ex.message.toString())
                Log.d("MainBooks", _ex.errorCode.toString())
                Log.d("MainBooks", _ex.errorDescription)

                _resultState.value = ResultState.Error(_ex.errorDescription)
            }
        }
    }


}