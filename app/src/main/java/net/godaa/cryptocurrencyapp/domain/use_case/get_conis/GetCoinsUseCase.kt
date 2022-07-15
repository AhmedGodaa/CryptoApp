package net.godaa.cryptocurrencyapp.domain.use_case.get_conis

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.godaa.cryptocurrencyapp.common.Resource
import net.godaa.cryptocurrencyapp.data.remote.dto.toCoin
import net.godaa.cryptocurrencyapp.domain.model.Coin
import net.godaa.cryptocurrencyapp.domain.repository.CoinRepo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repo.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred "))

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage?:"Couldn't reach server. Check your internet connections"))

        }
    }

}