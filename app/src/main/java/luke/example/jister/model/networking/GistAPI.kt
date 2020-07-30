package luke.example.jister.model.networking

import luke.example.jister.model.gist.GistDataResponseInfo
import retrofit2.Call
import retrofit2.http.GET

interface GistAPI {

        @GET("gists/public")
        fun getGistDataFromServer() : Call<GistDataResponseInfo>

}