package com.epf1.ontrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epf1.ontrack.entities.DriversChampionship
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DriversStandingsViewModel : ViewModel() {

    private val _standings = MutableStateFlow<List<DriversChampionship>>(emptyList())
    val standings: StateFlow<List<DriversChampionship>> = _standings

    init {
        fetchDriversStandings()
    }

    private fun fetchDriversStandings() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getDriversStandings(limit = 22, offset = 0)
                _standings.value = response.drivers_championship
            } catch (e: IOException) {
                println("Erro de IO: ${e.message}")
            } catch (e: HttpException) {
                println("Erro HTTP: ${e.message}")
            }
        }
    }
}