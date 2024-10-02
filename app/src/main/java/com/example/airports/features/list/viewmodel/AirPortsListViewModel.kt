package com.example.airports.features.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airports.data.model.AirPort
import com.example.airports.domain.AirPortListUseCase
import com.example.airports.domain.ResultType
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
    val state: StateFlow<AirPortListUiState> by lazy {
        airPortListUseCase()
            .map { mapResult(it) }
            .catch { emit(AirPortListUiState.Error) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AirPortListUiState.Loading
            )
    }
}

private fun mapResult(result: ResultType<List<AirPort>>) =
    when (result) {
        is ResultType.Success -> AirPortListUiState.Success(result.data)
        is ResultType.Error -> AirPortListUiState.Error
    }