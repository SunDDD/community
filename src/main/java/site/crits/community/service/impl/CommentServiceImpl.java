package site.crits.community.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.crits.community.dto.CommentDTO;
import site.crits.community.enums.CommentTypeEnum;
import site.crits.community.exception.CustomizeErrorCode;
import site.crits.community.exception.CustomizeException;
import site.crits.community.mapper.CommentMapper;
import site.crits.community.mapper.QuestionMapper;
import site.crits.community.mapper.UserMapper;
import site.crits.community.model.Comment;
import site.crits.community.model.Question;
import site.crits.community.model.User;
import site.crits.community.service.ICommentService;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public List<CommentDTO> listByQuestion(Integer id) {
        List<Comment> comments = commentMapper.selectByParentId(id.longValue());
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换为map
        List<User> users = new ArrayList<>();
        for (Integer userId : userIds) {
            User user = userMapper.findById(userId);
            users.add(user);
        }
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        System.out.println("****");
        Collections.reverse(commentDTOS);
        for (CommentDTO commentDTO : commentDTOS) {
            System.out.println(commentDTO);
        }
        return commentDTOS;

    }
}
