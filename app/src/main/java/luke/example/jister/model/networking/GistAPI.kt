package luke.example.jister.model.networking

import luke.example.jister.model.gist.GistDataResponseInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface GistAPI {

    @GET("gists/public")
    fun getGistDataFromServer(): Call<GistDataResponseInfo>

    @GET(" ")
    fun getGistFileFromServer(): Call<ResponseBody>

}