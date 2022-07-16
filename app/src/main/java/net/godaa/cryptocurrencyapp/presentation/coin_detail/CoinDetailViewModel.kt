package net.godaa.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.godaa.cryptocurrencyapp.common.Constants
import net.godaa.cryptocurrencyapp.common.Resource
import net.godaa.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import net.godaa.cryptocurrencyapp.domain.use_case.get_conis.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
//    instead using intent as bundle to store navigation information we will use savedStateHandle
//    save id of coin and when lunch activity get the coinId and show it's details
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
//        ?. check if it's not equal to null
        savedStateHandle.get<String>(Constants.KEY_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }

    }

    private fun getCoin(coinId: String) {

        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)

                }
                is Resource.Error -> {
                    _state.value =
                        CoinDetailState(error = result.message ?: "An unexpected error occurred")

                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}