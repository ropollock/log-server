package service

import ropollock.github.com.model.QueryParams
import java.time.format.DateTimeFormatter

interface MatchingService {
    val dateTimePattern : DateTimeFormatter
    fun findResults(logLines: List<String>, queryParams: QueryParams) : List<String>
}