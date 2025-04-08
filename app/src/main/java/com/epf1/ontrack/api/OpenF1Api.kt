import com.epf1.ontrack.responses.DriverResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenF1Api {
    @GET("api/drivers")
    fun getDrivers(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<DriverResponse>
}