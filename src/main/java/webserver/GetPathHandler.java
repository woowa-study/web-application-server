package webserver;

import get.GetDefaultPathHandler;
import get.GetShowUserListPathHandler;
import get.GetStaticResourceHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GetPathHandler implements PathHandler {
    private static final Map<String, PathHandleStrategy> pathHandlersMap = new HashMap<>();
    static {
        pathHandlersMap.put("", new GetDefaultPathHandler());
        pathHandlersMap.put("/", new GetDefaultPathHandler());
        pathHandlersMap.put("default", new GetStaticResourceHandler());
        pathHandlersMap.put("/user/list", new GetShowUserListPathHandler());
    }

    @Override
    public PathHandleStrategy getMatchingPathHandleStrategy(String requestUrl) {
        return Optional.ofNullable(this.pathHandlersMap.get(requestUrl)).orElse(this.pathHandlersMap.get(requestUrl));
    }
}
