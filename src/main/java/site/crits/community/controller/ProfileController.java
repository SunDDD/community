package site.crits.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import site.crits.community.dto.PaginationDTO;
import site.crits.community.model.User;
import site.crits.community.provider.QuestionProvider;
import site.crits.community.provider.UserProvider;
import site.crits.community.service.impl.ProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Carlos
 * @description 个人中心
 * @Date 2019/8/13
 */

@Controller
public class ProfileController {

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private QuestionProvider questionProvider;

    @Autowired
    private ProfileServiceImpl profileService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "10") Integer size) {

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");

            User user = (User) request.getSession().getAttribute("user");

            PaginationDTO pagination = profileService.list(page, size, user);
//        System.out.println(pagination);
            Integer totalPage = questionProvider.getTotalPage(size, user);
//        System.out.println("totalPage " + totalPage);
            List<Integer> pages = pagination.getPages();
//        System.out.println(pages);
            model.addAttribute("pagination", pagination);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPage", totalPage);


        } else if ("recent".equals(action)) {
            model.addAttribute("section", "recent");
            model.addAttribute("sectionName", "最新回复");
        }

        return "profile";
    }

}
