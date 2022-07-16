package net.godaa.cryptocurrencyapp.presentation.coin_detail

import net.godaa.cryptocurrencyapp.domain.model.Coin
import net.godaa.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val loading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
