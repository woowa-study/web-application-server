package webserver;

import post.PostSignInPathHandler;
import post.PostSignUpPathHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PostPathHandler implements PathHandler{
    private static final Map<String, PathHandleStrategy> pathHandlersMap = new HashMap<>();
    static {
        pathHandlersMap.put("/user/create", new PostSignUpPathHandler());
        pathHandlersMap.put("/user/login", new PostSignInPathHandler());
    }
    @Override
    public PathHandleStrategy getMatchingPathHandleStrategy(String requestUrl) {
        return Optional.of(this.pathHandlersMap.get(requestUrl)).orElse(this.pathHandlersMap.get(requestUrl));
    }
}
