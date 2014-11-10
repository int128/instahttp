package org.hidetake.httpserver

import spock.lang.Specification

class HttpServerClassSpec extends Specification {

    def "version() should return version"() {
        when:
        def version = HttpServer.version()

        then:
        version.matches(/instahttp-(@version@|SNAPSHOT|[0-9\.]+)/)
    }

}
