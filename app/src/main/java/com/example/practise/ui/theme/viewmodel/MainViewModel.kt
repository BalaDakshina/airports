package com.example.practise.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practise.domian.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {
    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                mainUseCase()
                _state.update {
                    "Success"
                }
            } catch (
                e: Exception
            ) {
                _state.update {
                    e.toString()
                }
            }

        }
    }
}