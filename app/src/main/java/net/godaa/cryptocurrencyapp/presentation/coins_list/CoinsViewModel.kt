package net.godaa.cryptocurrencyapp.presentation.coins_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.godaa.cryptocurrencyapp.common.Resource
import net.godaa.cryptocurrencyapp.domain.use_case.get_conis.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CoinsState())
    val state: State<CoinsState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {

        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinsState(coins = result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _state.value =
                        CoinsState(error = result.message ?: "An unexpected error occurred")

                }
                is Resource.Loading -> {
                    _state.value = CoinsState(loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}