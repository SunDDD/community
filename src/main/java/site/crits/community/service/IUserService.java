package site.crits.community.service;

import org.springframework.stereotype.Service;
import site.crits.community.dto.GithubUser;

@Service
public interface IUserService {

    /**
     * 根据id是否在数据库中，进行创建或者更新
     * @param githubUser
     * @return
     */
    public String createOrUpdate(GithubUser githubUser);
}
