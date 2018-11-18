package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import domain.HttpRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;
    private GlobalPathHandler globalPathHandler;

    public RequestHandler(Socket connectionSocket, GlobalPathHandler globalPathHandler) {
        this.connection = connectionSocket;
        this.globalPathHandler = globalPathHandler;
    }

    @Override
    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            DataOutputStream dos = new DataOutputStream(out);
            HttpRequestDto httpRequestDto = new HttpRequestDto(in);
            log.error("error !!!!, {}", httpRequestDto.getRequestUrl());
            globalPathHandler.getPathHandleStrategy(httpRequestDto.getHttpMethod(), httpRequestDto.getRequestUrl())
                    .response(httpRequestDto, dos);

        } catch (IOException | NoSuchMethodException e) {
            log.error(e.getMessage());
        }
    }
}
