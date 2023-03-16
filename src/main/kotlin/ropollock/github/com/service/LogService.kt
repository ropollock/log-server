package service

interface LogService {
    fun loadLogs(logPath: String)
    fun getLogLines() : List<String>
}