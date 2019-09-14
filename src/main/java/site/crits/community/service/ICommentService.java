package site.crits.community.service;

import site.crits.community.model.Comment;

public interface ICommentService {

    /**
     * 插入评论数据
     * @param comment
     */
    void insert(Comment comment);

}
