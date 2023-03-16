package ropollock.github.com.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResults(
    val matches: List<String>,
    val queryRequest: QueryRequest
)