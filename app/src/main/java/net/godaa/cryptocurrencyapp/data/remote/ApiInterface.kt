package net.godaa.cryptocurrencyapp.data.remote

import net.godaa.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import net.godaa.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET()
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}