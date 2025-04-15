import com.epf1.ontrack.responses.ConstructorsStandingsResponse
import com.epf1.ontrack.responses.DriverResponse
import com.epf1.ontrack.responses.DriversStandingsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenF1Api {
    @GET("api/drivers")
    suspend fun getDrivers(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): DriverResponse

    @GET("api/{year}/drivers-championship")
    suspend fun getDriversStandings(
        @Path("year") year: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): DriversStandingsResponse

    @GET("api/{year}/constructors-championship")
    suspend fun getConstructorsStandings(
        @Path("year") year: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ConstructorsStandingsResponse
}