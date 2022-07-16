package net.godaa.cryptocurrencyapp.presentation.coins_list

import net.godaa.cryptocurrencyapp.domain.model.Coin

data class CoinsState(
    val loading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
