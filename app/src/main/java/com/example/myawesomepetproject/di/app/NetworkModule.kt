package com.example.myawesomepetproject.di.app

import android.content.Context
import com.example.myawesomepetproject.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {
  
  @Module
  companion object {
    
    @JvmStatic
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
      return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
      }
    }
    
    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpCache(context: Context): Cache {
      val size: Long = 10 * 1024 * 1024 // 10 MB
      return Cache(context.cacheDir, size)
    }
    
    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: Interceptor,
        cache: Cache): OkHttpClient {
      return OkHttpClient.Builder()
          .cache(cache)
          .addInterceptor(loggingInterceptor)
          .build()
    }
    
    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
      return Retrofit.Builder()
          .client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .baseUrl(BuildConfig.BASE_URL)
          .build()
    }
  }
}