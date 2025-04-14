package com.epf1.ontrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epf1.ontrack.entities.Driver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {
    private val _drivers = MutableStateFlow<List<Driver>>(emptyList())
    val drivers: StateFlow<List<Driver>> = _drivers

    init {
        fetchDrivers()
    }

    private fun fetchDrivers() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getDrivers(10, 2)
                _drivers.value = response.drivers
            } catch (e: IOException) {
                println("Erro de IO: ${e.message}")
            } catch (e: HttpException) {
                println("Erro HTTP: ${e.message}")
            }
        }
    }
}