package com.example.airports.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airports.data.model.AirPort
import com.example.airports.domain.AirPortListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AirPortsListViewModel @Inject constructor(
    private val airPortListUseCase: AirPortListUseCase
) : ViewModel() {
    val state: StateFlow<List<AirPort>> by lazy {
        airPortListUseCase()
            .map { it }
            .catch { emit(emptyList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
    }
}