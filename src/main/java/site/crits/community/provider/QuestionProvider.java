package site.crits.community.provider;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.crits.community.dto.PaginationDTO;
import site.crits.community.dto.QuestionDTO;
import site.crits.community.mapper.QuestionMapper;
import site.crits.community.mapper.UserMapper;
import site.crits.community.model.Question;
import site.crits.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/13
 */

@Component
public class QuestionProvider {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(PaginationDTO paginationDTO, List<Question> questionList, Integer page, Integer size, Integer totalCount) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            Integer creator = question.getCreator();
//            System.out.println(creator);
            User user = userMapper.findById(creator);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
//        System.out.println(page);
        paginationDTO.setPagination(totalCount, page, size);
//        System.out.println(paginationDTO);
        return paginationDTO;
    }

    public Integer getTotalPage(Integer size) {
        Integer totalCount = questionMapper.questionCount();

        if (totalCount % size == 0) {
            return totalCount / size;
        } else {
            return totalCount / size + 1;
        }

    }

    public Integer getTotalPage(Integer size, User user) {

        Integer totalCount = questionMapper.questionCountByUser(user.getAccountId());

        int i = totalCount % size;
//        System.out.println("QuestionProvider.getTotalPage: " + totalCount + " " + i);
        if (i == 0) {
            return totalCount / size;
        } else {
            return totalCount / size + 1;
        }

    }

}
