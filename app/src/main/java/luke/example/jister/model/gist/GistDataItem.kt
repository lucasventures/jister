package luke.example.jister.model.gist

data class GistDataItem(
    val comments: Int,
    val comments_url: String,
    val commits_url: String,
    val created_at: String,
    val description: String,
    val files: Files,
    val forks_url: String,
    val git_pull_url: String,
    val git_push_url: String,
    val html_url: String,
    val id: String,
    val node_id: String,
    val owner: Owner,
    val `public`: Boolean,
    val truncated: Boolean,
    val updated_at: String,
    val url: String,
    val user: Any
)


/**
 * <<< Actual item from response object >>>
 *
 * {
"url": "https://api.github.com/gists/41249d6ae49b1f64476199431976b57f",
"forks_url": "https://api.github.com/gists/41249d6ae49b1f64476199431976b57f/forks",
"commits_url": "https://api.github.com/gists/41249d6ae49b1f64476199431976b57f/commits",
"id": "41249d6ae49b1f64476199431976b57f",
"node_id": "MDQ6R2lzdDQxMjQ5ZDZhZTQ5YjFmNjQ0NzYxOTk0MzE5NzZiNTdm",
"git_pull_url": "https://gist.github.com/41249d6ae49b1f64476199431976b57f.git",
"git_push_url": "https://gist.github.com/41249d6ae49b1f64476199431976b57f.git",
"html_url": "https://gist.github.com/41249d6ae49b1f64476199431976b57f",
"files": {
"docker-compose.yml": {
"filename": "docker-compose.yml",
"type": "text/x-yaml",
"language": "YAML",
"raw_url": "https://gist.githubusercontent.com/fenilmanani/41249d6ae49b1f64476199431976b57f/raw/363225c1da09ffcf1764be3a5eb00f1b61c8eb76/docker-compose.yml",
"size": 1074
}
},
"public": true,
"created_at": "2020-07-30T06:05:24Z",
"updated_at": "2020-07-30T06:05:24Z",
"description": "pi-hole and DNS over HTTPS docker-compose",
"comments": 0,
"user": null,
"comments_url": "https://api.github.com/gists/41249d6ae49b1f64476199431976b57f/comments",
"owner": {
"login": "fenilmanani",
"id": 68982772,
"node_id": "MDQ6VXNlcjY4OTgyNzcy",
"avatar_url": "https://avatars0.githubusercontent.com/u/68982772?v=4",
"gravatar_id": "",
"url": "https://api.github.com/users/fenilmanani",
"html_url": "https://github.com/fenilmanani",
"followers_url": "https://api.github.com/users/fenilmanani/followers",
"following_url": "https://api.github.com/users/fenilmanani/following{/other_user}",
"gists_url": "https://api.github.com/users/fenilmanani/gists{/gist_id}",
"starred_url": "https://api.github.com/users/fenilmanani/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/fenilmanani/subscriptions",
"organizations_url": "https://api.github.com/users/fenilmanani/orgs",
"repos_url": "https://api.github.com/users/fenilmanani/repos",
"events_url": "https://api.github.com/users/fenilmanani/events{/privacy}",
"received_events_url": "https://api.github.com/users/fenilmanani/received_events",
"type": "User",
"site_admin": false
},
"truncated": false
}
 */
