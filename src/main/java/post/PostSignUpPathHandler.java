package post;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.DataBase;
import domain.HttpRequestDto;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.PathHandleStrategy;
import webserver.ResponseHandlerUtil;

import java.io.DataOutputStream;

public class PostSignUpPathHandler implements PathHandleStrategy {
    private static final Logger log = LoggerFactory.getLogger(PostSignUpPathHandler.class);
    @Override
    public void response(HttpRequestDto httpRequestDto, DataOutputStream dos) {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(httpRequestDto.getData(), User.class);
        DataBase.addUser(user);
        log.debug(user.toString());

        ResponseHandlerUtil.response302Header(dos);
    }
}
