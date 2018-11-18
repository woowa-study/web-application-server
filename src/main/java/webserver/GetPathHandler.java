package webserver;

import get.GetDefaultPathHandler;
import get.GetStaticResourceHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GetPathHandler implements PathHandler {
    private static final Map<String, PathHandleStrategy> pathHandlersMap = new HashMap<>();
    static {
        pathHandlersMap.put("/", new GetDefaultPathHandler());
        pathHandlersMap.put("/index.html", new GetStaticResourceHandler());
        pathHandlersMap.put("/user/form.html", new GetStaticResourceHandler());
        pathHandlersMap.put("/user/login.html", new GetStaticResourceHandler());
    }

    @Override
    public PathHandleStrategy getMatchingPathHandleStrategy(String requestUrl) {
        return Optional.of(this.pathHandlersMap.get(requestUrl)).orElse(this.pathHandlersMap.get(requestUrl));
    }
}
