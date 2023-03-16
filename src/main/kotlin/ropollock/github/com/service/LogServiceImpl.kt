package service

import java.io.File

class LogServiceImpl : LogService {
    private var storage: List<String> = listOf()

    companion object {
        const val LOG_EXTENSION = ".log"
    }

    override fun loadLogs(logPath: String) {
        storage = emptyList()
        val logLines: MutableList<String> = mutableListOf()
        getLogFiles(logPath).forEach {
            logLines += it.bufferedReader().readLines()
        }
        storage += logLines
    }

    private fun getLogFiles(logPath: String) : Sequence<File> {
        val logFiles = File(logPath).walkTopDown().filter { it.name.endsWith(LOG_EXTENSION) }
        logFiles.forEach {
            println("Loading log $it")
        }
        return logFiles
    }

    override fun getLogLines() : List<String> {
        return storage
    }
}