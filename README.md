InstaHttp [![Build Status](https://travis-ci.org/int128/instahttp.svg?branch=master)](https://travis-ci.org/int128/instahttp)
=========

InstaHttp is an instant Web server based on [Jetty](http://eclipse.org/jetty/).

It provides HTTP access to static content in the local filesystem.


## How to Use

### Run the server

Download the release and run. It requires Java 6 or later.

```sh
java -jar instahttp.jar
```

Open `http://localhost:8080/`.

### Options

It accepts following options:

* `-b host` - listening host; default is `-b 0.0.0.0` (all interfaces)
* `-p port` - listening port; default is `-p 8080`
* `-l` - listening on localhost
* Extra argument - a base path of static content; default is `.` (current directory)

Example:

```sh
java -jar instahttp.jar -l ..
```


## How to Use in your App

InstaHttp is available on [Maven Central](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.hidetake%22%20AND%20a%3A%22instahttp%22) and [Bintray](https://bintray.com/int128/maven/instahttp).

```groovy
// Gradle
compile 'org.hidetake:instahttp:x.y'
```

Create and start a server by calling [`HttpServer.create()`](src/main/java/org/hidetake/httpserver/HttpServer.java) method.

```java
import org.eclipse.jetty.server.Server;
import org.hidetake.httpserver.HttpServer;
import java.net.InetSocketAddress;

Server server = HttpServer.create(new InetSocketAddress(8080), ".");
server.start();

server.stop();
```

See [`Main`](src/main/java/org/hidetake/httpserver/Main.java) class for example.


## Contribution

This is an open source software licensed under the Apache License Version 2.0. Send me your issue or pull request.
