package com.example.practise.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.practise.data.model.AirportDetail
import com.example.practise.domain.AirPortDetailsUseCase
import com.example.practise.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AirPortDetailsViewModel @Inject constructor(
    private val airPortDetailsUseCase: AirPortDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: StateFlow<AirportDetail> by lazy {
        airPortDetailsUseCase(savedStateHandle.toRoute<Screens.AirportDetails>().id)
            .map { it }
            .catch { Throwable("Error") }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AirportDetail()
            )
    }
}