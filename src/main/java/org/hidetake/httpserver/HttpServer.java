package org.hidetake.httpserver;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;

/**
 * A HTTP server.
 *
 * @author Hidetake Iwata
 */
public class HttpServer {
    private static final Logger LOG = Log.getLogger(HttpServer.class);

    /**
     * Create a HTTP server.
     *
     * @param address listening address
     * @param basePath base path of static content
     * @return a server
     */
    public static Server create(InetSocketAddress address, String basePath) {
        LOG.info("Version={}", version());
        LOG.info("BindAddress={}", address);
        LOG.info("BasePath={}", basePath);

        HandlerList handlers = new HandlerList();
        handlers.addHandler(accessLogHandler());
        handlers.addHandler(staticHandler(basePath));

        Server server = new Server(address);
        server.setHandler(handlers);
        return server;
    }

    public static Handler accessLogHandler() {
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        requestLogHandler.setRequestLog(new NCSARequestLog() {
            @Override
            public void write(String requestEntry) throws IOException {
                LOG.info(requestEntry);
            }
        });
        return requestLogHandler;
    }


    public static Handler staticHandler(String basePath) {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(basePath);
        resourceHandler.setDirectoriesListed(true);
        return resourceHandler;
    }

    public static String version() {
        InputStream stream = null;
        try {
            stream = HttpServer.class.getResourceAsStream("/version");
            if (stream != null) {
                byte[] buffer = new byte[64];
                int read = stream.read(buffer);
                return new String(buffer, 0, read);
            } else {
                LOG.debug("Could not find version resource");
                return "";
            }
        } catch (IOException e) {
            LOG.debug(e);
            return "";
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    LOG.debug(e);
                }
            }
        }
    }

    private HttpServer() {}
}
