package webserver;


import domain.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GlobalPathHandler {
    private static final Map<HttpMethod, PathHandler> pathHandlerMap = new HashMap<>();
    static {
        pathHandlerMap.put(HttpMethod.GET, new GetPathHandler());
        pathHandlerMap.put(HttpMethod.POST, new PostPathHandler());
    }
    public PathHandleStrategy getPathHandleStrategy(HttpMethod httpMethod, String requestUrl) throws NoSuchMethodException {
        return Optional.ofNullable(this.pathHandlerMap.get(httpMethod)
                .getMatchingPathHandleStrategy(requestUrl)).orElse(pathHandlerMap.get(HttpMethod.GET).getMatchingPathHandleStrategy("default"));
    }
}
