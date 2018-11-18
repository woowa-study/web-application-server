package webserver;

public interface PathHandler {
    PathHandleStrategy getMatchingPathHandleStrategy(String requestUrl);
}
