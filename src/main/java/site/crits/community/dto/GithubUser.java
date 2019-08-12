package site.crits.community.dto;

import lombok.Data;

/**
 * @author Carlos
 * @description Github用户信息
 * @Date 2019/8/10
 */

@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
