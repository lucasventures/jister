package luke.example.jister.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.model.networking.GistAPI
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
}