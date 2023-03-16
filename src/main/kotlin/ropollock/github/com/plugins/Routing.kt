package ropollock.github.com.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import ropollock.github.com.model.QueryParams
import ropollock.github.com.model.QueryRequest
import ropollock.github.com.model.SearchResults
import service.LogService
import service.MatchingService
import java.time.LocalDateTime

fun Application.configureRouting(logService: LogService, matchingService: MatchingService) {
    routing {
        get("/") {
            call.respondText("Log Server")
        }

        post("/logs/search") {
            val req = call.receive<QueryRequest>()
            val pattern = matchingService.dateTimePattern
            var fromTime: LocalDateTime? = null
            if (!req.fromTime.isNullOrBlank()) {
                fromTime = LocalDateTime.parse(req.fromTime, pattern)
            }

            var toTime: LocalDateTime? = null
            if (!req.toTime.isNullOrBlank()) {
                toTime = LocalDateTime.parse(req.toTime, pattern)
            }

            val results = matchingService.findResults(
                logService.getLogLines(),
                QueryParams(req.queryRegex.toRegex(), fromTime, toTime)
            )
            call.respond(HttpStatusCode.OK, SearchResults(results, req))
        }
    }
}
