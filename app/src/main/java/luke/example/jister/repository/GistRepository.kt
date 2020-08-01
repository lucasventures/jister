package luke.example.jister.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.model.networking.GistAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GistRepository : BaseRepository() {
    private val gistAPICaller: GistAPI
        get() = getRetrofitInstance()!!.create(GistAPI::class.java)

    fun getGistData(liveData: MutableLiveData<GistDataResponseInfo>) {
        //just fetch from server until RoomDB is implemented
        gistAPICaller.getGistDataFromServer().enqueue(object : Callback<GistDataResponseInfo> {
            override fun onFailure(call: Call<GistDataResponseInfo>, t: Throwable) {
                Log.d(TAG, "onFailure: Call for gists failed. ${t.message}")
                Log.e(TAG, "onFailure: Call for gists failed. ${t.message}")
            }

            override fun onResponse(
                call: Call<GistDataResponseInfo>,
                response: Response<GistDataResponseInfo>
            ) {
                liveData.postValue(response.body())
            }

        })
    }

    internal fun formatGistFileUrl(fileUrl: String): String {
        return if (fileUrl[fileUrl.length - 1] != '/') {
            "$fileUrl/"
        } else {
            fileUrl
        }
    }

    fun getGistFile(fileUrl: String, liveData: MutableLiveData<ResponseBody>) {
        val formattedUrl = formatGistFileUrl(fileUrl)

        val caller = getGistRetrofitInstance(formattedUrl)!!.create(GistAPI::class.java)
        //just fetch from server until RoomDB is implemented
        caller.getGistFileFromServer().enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "onFailure: Call for gists failed. ${t.message}")
                Log.e(TAG, "onFailure: Call for gists failed. ${t.message}")
            }
        })
    }
}