package site.crits.community.service;

import site.crits.community.dto.CommentDTO;
import site.crits.community.model.Comment;

import java.util.List;

public interface ICommentService {

    /**
     * 插入评论数据
     * @param comment
     */
    void insert(Comment comment);

    /**
     * 根据问题返回评论列表
     * @param id
     */
    List<CommentDTO> listByQuestion(Integer id);
}
