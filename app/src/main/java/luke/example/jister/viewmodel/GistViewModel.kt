package luke.example.jister.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.repository.GistRepository

class GistViewModel(private val repository: GistRepository): ViewModel() {
    var gistData = MutableLiveData<GistDataResponseInfo>()

    fun getLatestGistData() {
        repository.getGistData(gistData)
    }

}