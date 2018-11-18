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

public class PostSignInPathHandler implements PathHandleStrategy {
    private static final Logger log = LoggerFactory.getLogger(PostSignUpPathHandler.class);
    @Override
    public void response(HttpRequestDto httpRequestDto, DataOutputStream dos) {
        ObjectMapper mapper = new ObjectMapper();
        User loginRequestUser = mapper.convertValue(httpRequestDto.getData(), User.class);
        User user = DataBase.findUserById(loginRequestUser.getUserId());

        if(user == null) {
            log.error("유저 정보가 존재하지 않습니다.");
            ResponseHandlerUtil.responseLoginFailed302Header(dos, "user/login.html");
        }

        if(user.matchPassword(loginRequestUser)) {
            log.info("로그인 성공");
            ResponseHandlerUtil.responseLoginSuccess302Header(dos, "index.html");
            return;
        }
        log.error("비밀번호가 틀렸습니다.");
        ResponseHandlerUtil.responseLoginFailed302Header(dos, "user/login.html");
    }
}
