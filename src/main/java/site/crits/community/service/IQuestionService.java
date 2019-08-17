package site.crits.community.service;

import site.crits.community.dto.PaginationDTO;
import site.crits.community.dto.QuestionDTO;
import site.crits.community.model.Question;

public interface IQuestionService {

    /**
     * 生成问题列表，并和用户关联
     * @return
     * @param page
     * @param size
     */
    PaginationDTO list(Integer page, Integer size);

    /**
     * 根据id返回问题详情
     * @param id
     * @return
     */
    QuestionDTO questionDetail(Integer id);

    /**
     * 根据id判断插入数据库或更新数据库
     * @param question
     */
    void createOrUpdate(Question question);

    /**
     * 评论数加一
     * @param id 问题id
     */
    void incView(Integer id);
}
