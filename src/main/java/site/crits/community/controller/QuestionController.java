package site.crits.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.crits.community.dto.QuestionDTO;
import site.crits.community.service.impl.QuestionServiceImpl;

/**
 * @author Carlos
 * @description 问题详情
 * @Date 2019/8/16
 */

@Controller
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {

        QuestionDTO questionDTO = questionService.questionDetail(id);
        model.addAttribute("question", questionDTO);

        return "question";

    }

}
