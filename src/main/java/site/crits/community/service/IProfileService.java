package site.crits.community.service;

import site.crits.community.dto.PaginationDTO;
import site.crits.community.model.User;

public interface IProfileService {

    /**
     * 通过用户查询问题列表
     * @param page
     * @param size
     * @param user
     * @return
     */
    PaginationDTO list(Integer page, Integer size, User user);

}
