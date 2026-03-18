package se.grit.erik.cveye.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import se.grit.erik.cveye.model.NvdResponse

interface NvdApiService {

    @GET("rest/json/cves/2.0")
    suspend fun getLatestVulnerabilities(
        @Query("resultsPerPage") resultsPerPage: Int = 10,
        @Query("pubStartDate") pubStartDate: String,
        @Query("pubEndDate") pubEndDate: String
    ): Response<NvdResponse>
}