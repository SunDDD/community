package site.crits.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.crits.community.enums.CommentTypeEnum;
import site.crits.community.exception.CustomizeErrorCode;
import site.crits.community.exception.CustomizeException;
import site.crits.community.mapper.CommentMapper;
import site.crits.community.mapper.QuestionMapper;
import site.crits.community.model.Comment;
import site.crits.community.model.Question;
import site.crits.community.service.ICommentService;

/**
 * @author Carlos
 * @description 评论
 * @Date 2019/8/30
 */

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionServiceImpl questionService;

    @Override
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            Question question = questionMapper.getById(comment.getParentId().intValue());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionService.incCommentCount(question.getId());

        }
    }
}
