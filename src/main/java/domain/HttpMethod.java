package domain;

public enum HttpMethod {
    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), PATCH("PATCH");
    private String method;
    HttpMethod(String method) {
        this.method = method;
    }
    public String getMethod() {
        return method;
    }
}
