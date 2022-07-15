package net.godaa.cryptocurrencyapp.domain.repository

import net.godaa.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import net.godaa.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepo {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(id:String) : CoinDetailDto
}