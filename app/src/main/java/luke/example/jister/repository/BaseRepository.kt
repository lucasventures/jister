package luke.example.jister.repository

import androidx.lifecycle.ViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier

abstract class BaseRepository : ViewModel() {

    private val baseUrl = "https://api.github.com/"
    private var retrofitInstance: Retrofit? = null
    private val appJson = "application/json"

    protected fun getRetrofitInstance(): Retrofit? {
        return getRetrofitInstance(baseUrl)
    }

    protected fun getGistRetrofitInstance(fileUrl: String): Retrofit? {
        return getRetrofitInstance(fileUrl)
    }

    private fun getRetrofitInstance(url: String): Retrofit? {
        if (retrofitInstance != null) {
            return retrofitInstance
        }

        val client = getHTTPClient()
        val headerAuthorizationInterceptor = Interceptor {
            var request = it.request()
            val headerAcceptor = request.headers.newBuilder()
            headerAcceptor.add("Content-Type", appJson)
            request = request.newBuilder().headers(headerAcceptor.build()).build()
            it.proceed(request)
        }

        client.addInterceptor(headerAuthorizationInterceptor)

        client.hostnameVerifier(HostnameVerifier { hostname, session ->
            true
        })

        retrofitInstance = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        return retrofitInstance
    }


    private fun getHTTPClient(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
    }
}
