package luke.example.jister.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.repository.GistRepository

class GistViewModel(): ViewModel() {
    var gistData = MutableLiveData<GistDataResponseInfo>()
    private lateinit var repository: GistRepository

    fun setRepository(repo:GistRepository){
        repository = repo
    }

    fun getLatestGistData() {
        repository.getGistData(gistData)
    }

}