package net.godaa.cryptocurrencyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.godaa.cryptocurrencyapp.common.Constants.BASE_URL
import net.godaa.cryptocurrencyapp.data.remote.ApiInterface
import net.godaa.cryptocurrencyapp.data.repository.CoinRepoImpl
import net.godaa.cryptocurrencyapp.domain.repository.CoinRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesCoinRepo(api: ApiInterface): CoinRepo {
//        i want to inject ConRepoImpl and it's need a argument in constructor
//        the needed Argument is ApiInterface object
        return CoinRepoImpl(api)
    }


}