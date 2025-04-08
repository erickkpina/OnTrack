package com.epf1.ontrack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.epf1.ontrack.databinding.ActivityMainBinding
import com.epf1.ontrack.responses.DriverResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDrivers()
    }

    private fun getDrivers() {
        RetrofitInstance.api.getDrivers(limit = 10, offset = 20)
            .enqueue(object : Callback<DriverResponse> {
                override fun onResponse(call: Call<DriverResponse>, response: Response<DriverResponse>) {
                    if (response.isSuccessful) {
                        val drivers = response.body()?.drivers
                        var temp = ""
                        if (drivers != null) {
                            for (d in drivers) {
                                temp += "Driver: ${d.name} ${d.surname} - ${d.nationality}\n"
                            }
                            binding.drivers.text = temp
                        } else {
                            println("drivers == null")
                        }
                    } else {
                        println("Erro na resposta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<DriverResponse>, t: Throwable) {
                    println("Erro na requisição: ${t.message}")
                }
            })
    }
}