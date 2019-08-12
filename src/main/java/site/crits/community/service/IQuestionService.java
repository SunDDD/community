package site.crits.community.service;

import site.crits.community.dto.PaginationDTO;

public interface IQuestionService {

    /**
     * 生成问题列表，并和用户关联
     * @return
     * @param page
     * @param size
     */
    PaginationDTO list(Integer page, Integer size);

    /**
     * 获取总页数
     * @return
     * @param size
     */
    Integer getTotalPage(Integer size);

}
