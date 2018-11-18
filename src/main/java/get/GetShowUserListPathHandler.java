package get;

import db.DataBase;
import domain.HttpRequestDto;
import model.User;
import webserver.PathHandleStrategy;
import webserver.ResponseHandlerUtil;

import java.io.DataOutputStream;
import java.util.Iterator;

public class GetShowUserListPathHandler implements PathHandleStrategy {
    @Override
    public void response(HttpRequestDto httpRequestDto, DataOutputStream dos) {
        Iterator<User> userIterator = DataBase.findAll().iterator();
        StringBuilder st = new StringBuilder();
        while(userIterator.hasNext()) {
            User user = userIterator.next();
            st.append("이름 : " + user.getName());
            st.append(System.lineSeparator());
            st.append("이메일 : " + user.getEmail());
            st.append(System.lineSeparator());
        }
        if(Boolean.parseBoolean(httpRequestDto.getCookies().get("Cookie: logined"))) {
            ResponseHandlerUtil.response200Header(dos, st.toString().getBytes().length);
            ResponseHandlerUtil.responseBody(dos, st.toString().getBytes());
            return;
        }
        ResponseHandlerUtil.responseLoginFailed302Header(dos, "user/login.html");
    }
}
