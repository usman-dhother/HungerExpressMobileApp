package com.teamd.hungerexpressfooddelivery.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.teamd.hungerexpressfooddelivery.data.api.RestaurantApi
import com.teamd.hungerexpressfooddelivery.data.api.UserApi
import com.teamd.hungerexpressfooddelivery.utils.constants.ConstNames.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    // annotation
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRestaurantApi(retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }
}