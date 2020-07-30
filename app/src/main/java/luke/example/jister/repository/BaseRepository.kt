package luke.example.jister.repository

import androidx.lifecycle.ViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepository : ViewModel() {

    private val BASE_URL = "https://www.api.github.com/"
    private var retrofitInstance: Retrofit? = null
    private val APPLICATION_JSON = "application/json"

    protected fun getRetrofitInstance(): Retrofit? {
        if (retrofitInstance != null) {
            return retrofitInstance
        }

        val client = getHTTPClient()
        val headerAuthorizationInterceptor = Interceptor {
            var request = it.request()
            val headerAcceptor = request.headers.newBuilder()
            headerAcceptor.add("Content-Type", APPLICATION_JSON)
            headerAcceptor.add("API-Version", "2.0")
            request = request.newBuilder().headers(headerAcceptor.build()).build()
            it.proceed(request)
        }

        client.addInterceptor(headerAuthorizationInterceptor)

        retrofitInstance = Retrofit.Builder()
            .baseUrl(getBaseURL())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        return retrofitInstance
    }

    private fun getBaseURL(): String {
        return BASE_URL
    }

    protected fun getHTTPClient(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
    }
}
