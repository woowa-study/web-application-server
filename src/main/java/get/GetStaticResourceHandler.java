package get;

import domain.HttpRequestDto;
import webserver.PathHandleStrategy;
import webserver.ResponseHandlerUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetStaticResourceHandler implements PathHandleStrategy {
    @Override
    public void response(HttpRequestDto httpRequestDto, DataOutputStream dos) {
        byte[] body;
        try {
            body = Files.readAllBytes(Paths.get("/Users/kimkyunam/Documents/workspace/web-application-server/webapp" + httpRequestDto.getRequestUrl()));
            ResponseHandlerUtil.response200Header(dos, body.length);
            ResponseHandlerUtil.responseBody(dos, body);
        } catch (IOException e) {
            e.printStackTrace();
            ResponseHandlerUtil.response404Header(dos);
        }

    }
}
