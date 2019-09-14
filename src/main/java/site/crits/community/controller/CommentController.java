package site.crits.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.crits.community.dto.CommentDTO;
import site.crits.community.dto.ResultDTO;
import site.crits.community.exception.CustomizeErrorCode;
import site.crits.community.model.Comment;
import site.crits.community.model.User;
import site.crits.community.service.impl.CommentServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos
 * @description 回复
 * @Date 2019/8/29
 */

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
