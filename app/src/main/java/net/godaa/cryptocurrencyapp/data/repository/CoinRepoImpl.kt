package net.godaa.cryptocurrencyapp.data.repository

import net.godaa.cryptocurrencyapp.data.remote.ApiInterface
import net.godaa.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import net.godaa.cryptocurrencyapp.data.remote.dto.CoinDto
import net.godaa.cryptocurrencyapp.domain.repository.CoinRepo
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(
    private val api: ApiInterface
) : CoinRepo {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(id: String): CoinDetailDto {
        return api.getCoinById(id)
    }

}