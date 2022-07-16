package net.godaa.cryptocurrencyapp.domain.use_case.get_coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.godaa.cryptocurrencyapp.common.Resource
import net.godaa.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import net.godaa.cryptocurrencyapp.data.remote.dto.toCoin
import net.godaa.cryptocurrencyapp.data.remote.dto.toCoinDetail
import net.godaa.cryptocurrencyapp.domain.model.Coin
import net.godaa.cryptocurrencyapp.domain.model.CoinDetail
import net.godaa.cryptocurrencyapp.domain.repository.CoinRepo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repo.getCoinById(id).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(
                Resource.Error(e.localizedMessage ?: "An unexpected error occurred ")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connections"
                )
            )

        }
    }

}