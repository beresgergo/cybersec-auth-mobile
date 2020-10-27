package edu.oe.nik.cyber.auth.mobile.di.modules.network

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.network.login.LoginApi
import edu.oe.nik.cyber.auth.mobile.network.management.ManagementApi
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {
    private const val BASE_URL: String = "https://192.16.0.9:8080"

    @Provides
    @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRegistrationService(retrofit: Retrofit): RegistrationApi = retrofit.create(RegistrationApi::class.java)

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideManagementService(retrofit: Retrofit): ManagementApi = retrofit.create(ManagementApi::class.java)
}