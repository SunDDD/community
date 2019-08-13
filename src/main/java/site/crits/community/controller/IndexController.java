package site.crits.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.crits.community.dto.PaginationDTO;
import site.crits.community.mapper.UserMapper;
import site.crits.community.model.User;
import site.crits.community.provider.QuestionProvider;
import site.crits.community.provider.UserProvider;
import site.crits.community.service.impl.QuestionServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/10
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private QuestionProvider questionProvider;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size) {

        Cookie[] cookies = request.getCookies();

        User user = userProvider.findUserByCookies(cookies);
        if (user != null) {
            request.getSession().setAttribute("user" , user);
        }

        model.addAttribute("currentPage", page);
        PaginationDTO pagination = questionService.list(page, size);
        Integer totalPage = questionProvider.getTotalPage(size);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pagination", pagination);

        return "index";
    }

}
