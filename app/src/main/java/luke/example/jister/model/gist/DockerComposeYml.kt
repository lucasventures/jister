package luke.example.jister.model.gist

data class DockerComposeYml(
    val filename: String,
    val language: String,
    val raw_url: String,
    val size: Int,
    val type: String
)