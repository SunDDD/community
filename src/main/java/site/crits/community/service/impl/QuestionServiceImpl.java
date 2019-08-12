package site.crits.community.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.crits.community.dto.PaginationDTO;
import site.crits.community.dto.QuestionDTO;
import site.crits.community.mapper.QuestionMapper;
import site.crits.community.mapper.UserMapper;
import site.crits.community.model.Question;
import site.crits.community.model.User;
import site.crits.community.service.IQuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/12
 */

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PaginationDTO list(Integer page, Integer size) {

        //size * (page - 1)
        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            String creator = question.getCreator();
//            System.out.println(creator);
            User user = userMapper.findByAccountId(creator);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.questionCount();
        System.out.println(page);
        paginationDTO.setPagination(totalCount, page, size);
        System.out.println(paginationDTO);
        return paginationDTO;

    }

    @Override
    public Integer getTotalPage(Integer size) {
        Integer totalCount = questionMapper.questionCount();

        if (totalCount / size == 0) {
            return totalCount / size;
        } else {
            return totalCount / size + 1;
        }

    }
}
