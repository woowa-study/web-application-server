package domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpRequestDto {
    private static final Logger log = LoggerFactory.getLogger(HttpRequestDto.class);
    private HttpMethod httpMethod;
    private String requestUrl;
    private byte[] body;
    private Map<String, String> cookies;
    private Integer contentLength;
    private Map<String, String> data;

    public HttpRequestDto(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = br.readLine();
        this.httpMethod = HttpRequestUtils.getMethod(line);
        this.requestUrl = HttpRequestUtils.getRequestUrl(line);
        while(!line.equals("")) {
            line = br.readLine();
            if(line.contains("Content-Length")) {
                this.contentLength = Integer.parseInt(line.split(" ")[1]);
            }
            if(line.contains("Cookie")) {
                this.cookies = HttpRequestUtils.parseCookies(line);
            }
        }
        if(this.httpMethod == HttpMethod.POST) {
            handlerPost(br);
        }
    }

    private void handlerPost(BufferedReader br) throws IOException {
        String body = IOUtils.readData(br, (this.contentLength));
        this.data = HttpRequestUtils.parseQueryString(body);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public byte[] getBody() {
        return body;
    }

    public Integer getContentLength() {
        return contentLength;
    }

    public Map<String, String> getData() {
        return data;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}
