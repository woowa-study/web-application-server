package domain;


import util.HttpRequestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpRequestDto {
    private HttpMethod httpMethod;
    private String requestUrl;
    private byte[] body;
    private Integer contentLength;
    private Map<String, String> queryStrings;

    public HttpRequestDto(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = br.readLine();
        this.httpMethod = HttpRequestUtils.getMethod(line);
        this.requestUrl = HttpRequestUtils.getRequestUrl(line);
        while(!line.equals("")) {
            line = br.readLine();
        }
        System.out.print(line);
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

    public Map<String, String> getQueryStrings() {
        return queryStrings;
    }
}
