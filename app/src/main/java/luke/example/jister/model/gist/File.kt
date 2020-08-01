package luke.example.jister.model.gist

import java.io.Serializable

data class File(
    val filename: String,
    val language: String,
    val raw_url: String,
    val size: Int,
    val type: String
) : Serializable