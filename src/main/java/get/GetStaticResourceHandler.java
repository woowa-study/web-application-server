package get;

import domain.HttpRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.PathHandleStrategy;
import webserver.ResponseHandlerUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetStaticResourceHandler implements PathHandleStrategy {
    private static final Logger log = LoggerFactory.getLogger(GetStaticResourceHandler.class);
    @Override
    public void response(HttpRequestDto httpRequestDto, DataOutputStream dos) {
        byte[] body;
        try {
            body = Files.readAllBytes(Paths.get("/Users/kimkyunam/Documents/workspace/web-application-server/webapp" + httpRequestDto.getRequestUrl()));
            log.info("request url : {}", httpRequestDto.getRequestUrl());
            if(httpRequestDto.getAccept() != null && httpRequestDto.getAccept().equals("text/css")) {
                ResponseHandlerUtil.response200HeaderForStyleSheet(dos, body.length, "text/css");
                ResponseHandlerUtil.responseBody(dos, body);
                return;
            }
            ResponseHandlerUtil.response200Header(dos, body.length);
            ResponseHandlerUtil.responseBody(dos, body);
        } catch (IOException e) {
            e.printStackTrace();
            ResponseHandlerUtil.response404Header(dos);
        }

    }
}
