# GREP LogServer
A simple Ktor server that allows searching of log files on the server via API. 
No authentication required.

## Usage
POST /logs/search

Request Body
```json
{
    "queryRegex": "unicomp[0-9]",
    "fromTime": "01/Jul/1995:00:00:09 -0400",
    "toTime": "03/Jul/1995:21:41:16 -0400"
}
```
Response

```json
{
    "matches": [
        "unicomp6.unicomp.net - - [01/Jul/1995:00:00:14 -0400] \"GET /shuttle/countdown/count.gif HTTP/1.0\" 200 40310",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:00:14 -0400] \"GET /images/NASA-logosmall.gif HTTP/1.0\" 200 786",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:00:14 -0400] \"GET /images/KSC-logosmall.gif HTTP/1.0\" 200 1204",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:01:41 -0400] \"GET /htbin/cdt_main.pl HTTP/1.0\" 200 3214",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:02:17 -0400] \"GET /facilities/lcc.html HTTP/1.0\" 200 2489",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:02:20 -0400] \"GET /images/ksclogosmall.gif HTTP/1.0\" 200 3635",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:02:21 -0400] \"GET /images/kscmap-tiny.gif HTTP/1.0\" 200 2537",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:03:09 -0400] \"GET /images/lcc-small2.gif HTTP/1.0\" 200 58026",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:04:16 -0400] \"GET /ksc.html HTTP/1.0\" 200 7074",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:04:19 -0400] \"GET /images/ksclogo-medium.gif HTTP/1.0\" 200 5866",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:04:26 -0400] \"GET /images/USA-logosmall.gif HTTP/1.0\" 200 234",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:04:26 -0400] \"GET /images/WORLD-logosmall.gif HTTP/1.0\" 200 669",
        "unicomp6.unicomp.net - - [01/Jul/1995:00:04:27 -0400] \"GET /images/MOSAIC-logosmall.gif HTTP/1.0\" 200 363",
        "unicomp5.unicomp.net - - [03/Jul/1995:13:39:06 -0400] \"GET /shuttle/ HTTP/1.0\" 200 957",
        "unicomp5.unicomp.net - - [03/Jul/1995:13:39:27 -0400] \"GET /shuttle/countdown/ HTTP/1.0\" 200 3985",
        "unicomp3.unicomp.net - - [03/Jul/1995:21:40:17 -0400] \"GET /shuttle/missions/sts-71/images/images.html HTTP/1.0\" 200 7634",
        "unicomp3.unicomp.net - - [03/Jul/1995:21:41:16 -0400] \"GET /shuttle/missions/sts-71/images/KSC-95EC-0917.txt HTTP/1.0\" 200 1392"
    ],
    "queryRequest": {
        "queryRegex": "unicomp[0-6]",
        "fromTime": "01/Jul/1995:00:00:09 -0400",
        "toTime": "03/Jul/1995:21:41:16 -0400"
    }
}
```

## Configuration
To search logs the `application.conf` file needs to have `logs.path` pointing to the directory containing the log data.
The directory will be searched recursively for files matching `*.log` and their data loaded for searching.

application.conf under resources folder.
```hocon
logs {
    path = /tmp/NASA_access_log/
}
```

The server port can also be configured in the `application.conf`, the default is `8080`.

## Building
After the `application.conf` has been updated. You must run gradle build and then the `buildFatJar` task to create an executable jar.

Build application.
```bash
./gradlew build
```

Build fat jar executable.
```bash
./gradlew buildFatJar
```

## Running
After creating the application fat jar you can just run it using command line `java`.
The fat jar should be named something like `log-server-all.jar` under `build/libs/`.

```bash
java -jar log-server-all.jar
```

Once started you should see output like this and the server will be ready to handle requests.
```bash
$ java -jar log-server-all.jar
2023-03-16 13:48:16.679 [main]  INFO  Application - Autoreload is disabled because the development mode is off.
Log path: /tmp/NASA_access_log/
Loading log \tmp\NASA_access_log\August_1995\nasa_august_1995.log
Loading log \tmp\NASA_access_log\July_1995\nasa_july_1995.log
2023-03-16 13:48:17.878 [main]  INFO  Application - Application started in 1.226 seconds.
2023-03-16 13:48:17.878 [main]  INFO  Application - Application started: io.ktor.server.application.Application@697446d4
2023-03-16 13:48:18.514 [DefaultDispatcher-worker-1]  INFO  Application - Responding at http://127.0.0.1:8080
```
