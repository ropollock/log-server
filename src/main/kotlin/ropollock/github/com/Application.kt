package ropollock.github.com

import io.ktor.server.application.*
import io.ktor.server.netty.*
import ropollock.github.com.plugins.*
import service.LogService
import service.LogServiceImpl
import service.MatchingService
import service.MatchingServiceImpl

fun Application.stringProperty(path: String): String =
    this.environment.config.property(path).getString()

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    val logPath = stringProperty("logs.path")
    println("Log path: $logPath")
    val logService = getLogService()
    val matchingService = getMatchingService()
    logService.loadLogs(logPath)

    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureRouting(logService, matchingService)
}

fun getLogService() : LogService {
    return LogServiceImpl()
}

fun getMatchingService() : MatchingService {
    return MatchingServiceImpl()
}