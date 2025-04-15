package com.epf1.ontrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epf1.ontrack.entities.ConstructorsChampionship
import com.epf1.ontrack.entities.DriversChampionship
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.Year

class StandingsViewModel : ViewModel() {

    private val _driversStandings = MutableStateFlow<List<DriversChampionship>>(emptyList())
    val driversStandings: StateFlow<List<DriversChampionship>> = _driversStandings

    private val _constructorsStandings = MutableStateFlow<List<ConstructorsChampionship>>(emptyList())
    val constructorsStandings: StateFlow<List<ConstructorsChampionship>> = _constructorsStandings

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var currentYear = Year.now().value

    init {
        fetchStandings(currentYear)
    }

    fun fetchStandings(year: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                coroutineScope {
                    val driversDeferred = async {
                        RetrofitInstance.api.getDriversStandings(limit = 100, offset = 0, year = year)
                    }
                    val constructorsDeferred = async {
                        RetrofitInstance.api.getConstructorsStandings(limit = 100, offset = 0, year = year)
                    }

                    val driversResponse = driversDeferred.await()
                    val constructorsResponse = constructorsDeferred.await()

                    _driversStandings.value = driversResponse.drivers_championship
                    _constructorsStandings.value = constructorsResponse.constructors_championship
                }
            } catch (e: IOException) {
                println("Erro de IO: ${e.message}")
            } catch (e: HttpException) {
                println("Erro HTTP: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCurrentYear(): Int = currentYear
}