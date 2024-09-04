package com.example.practise.domian

import com.example.practise.data.MainRepositoryImpl
import javax.inject.Inject

class MainUseCase @Inject constructor(
   private val mainRepositoryImpl: MainRepositoryImpl
) {
    suspend operator fun invoke() {
        mainRepositoryImpl.getUiData()
    }
}