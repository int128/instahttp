package org.hidetake.httpserver

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import org.eclipse.jetty.server.Server
import spock.lang.Shared
import spock.lang.Specification

class HttpServerSpec extends Specification {

    @Shared
    Server server

    @Shared
    String endpoint

    def setupSpec() {
        int port = Utility.pickUpFreePort()
        endpoint = "http://localhost:$port"
        server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), port), '.')
        server.start()
    }

    def cleanupSpec() {
        server.stop()
    }

    def "server should response a HTML on /"() {
        when:
        def response = new RESTClient(endpoint).get(path: '/') as HttpResponseDecorator

        then:
        response.status == 200
        response.contentType == 'text/html'
        response.entity.contentLength > 100
    }

}
