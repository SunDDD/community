package site.crits.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.crits.community.dto.GithubUser;
import site.crits.community.mapper.UserMapper;
import site.crits.community.model.User;
import site.crits.community.service.IUserService;

import java.util.UUID;

/**
 * @author Carlos
 * @description 用户操作
 * @Date 2019/8/16
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String createOrUpdate(GithubUser githubUser) {
        String accountId = String.valueOf(githubUser.getId());
        User user = userMapper.findByAccountId(accountId);
        String token = UUID.randomUUID().toString();
        if (user != null) {
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setBio(githubUser.getBio());
            user.setName(githubUser.getName());

            user.setToken(token);
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateByUser(user);
        } else {
            user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);
        }
        return token;
    }
}
