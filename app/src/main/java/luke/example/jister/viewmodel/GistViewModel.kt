package luke.example.jister.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.repository.GistRepository
import okhttp3.ResponseBody

class GistViewModel : ViewModel() {
    var gistData = MutableLiveData<GistDataResponseInfo>()
    var gistFile = MutableLiveData<ResponseBody>()

    //todo: Can be improved, but fine for now
    private lateinit var repository: GistRepository
    fun setRepository(repo: GistRepository) {
        repository = repo
    }

    fun getLatestGistData() {
        repository.getGistData(gistData)
    }

    fun getGistFile(fileUrl: String) {
        repository.getGistFile(fileUrl, gistFile)
    }

}