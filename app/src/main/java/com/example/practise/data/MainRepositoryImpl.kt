package com.example.practise.data

import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) {
    suspend fun getUiData() = mainService.getData()
}