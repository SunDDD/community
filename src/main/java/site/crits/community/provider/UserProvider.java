package site.crits.community.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.crits.community.mapper.UserMapper;
import site.crits.community.model.User;

import javax.servlet.http.Cookie;

/**
 * @author Carlos
 * @description 通过Cookies查找用户
 * @Date 2019/8/13
 */

@Component
public class UserProvider {

    @Autowired
    private UserMapper userMapper;

    public User findUserByCookies(Cookie[] cookies) {

        User user = null;
        if (cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    break;

                }
            }
        }

        return user;
    }

}
