package site.crits.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.crits.community.dto.CommentDTO;
import site.crits.community.dto.QuestionDTO;
import site.crits.community.service.impl.CommentServiceImpl;
import site.crits.community.service.impl.QuestionServiceImpl;

import java.util.List;

/**
 * @author Carlos
 * @description 问题详情
 * @Date 2019/8/16
 */

@Controller
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {

        QuestionDTO questionDTO = questionService.questionDetail(id);
        //累加阅读数
        questionService.incView(id);
        List<CommentDTO> comments = commentService.listByQuestion(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);

        return "question";

    }

}
