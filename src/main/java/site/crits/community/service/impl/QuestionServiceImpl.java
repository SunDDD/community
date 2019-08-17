package site.crits.community.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.crits.community.dto.PaginationDTO;
import site.crits.community.dto.QuestionDTO;
import site.crits.community.exception.CustomizeErrorCode;
import site.crits.community.exception.CustomizeException;
import site.crits.community.mapper.QuestionMapper;
import site.crits.community.mapper.UserMapper;
import site.crits.community.model.Question;
import site.crits.community.model.User;
import site.crits.community.provider.QuestionProvider;
import site.crits.community.service.IQuestionService;

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

    @Autowired
    private QuestionProvider questionProvider;

    @Override
    public PaginationDTO list(Integer page, Integer size) {

        //size * (page - 1)
        Integer offset = size * (page - 1);

        PaginationDTO paginationDTO = new PaginationDTO();
        List<Question> questionList = questionMapper.list(offset, size);
        Integer totalCount = questionMapper.questionCount();
//        System.out.println("Q totalCount: " + totalCount);
//        System.out.println(questionList);
        PaginationDTO dto = questionProvider.list(paginationDTO, questionList, page, size, totalCount);
        return dto;

    }

    @Override
    public QuestionDTO questionDetail(Integer id) {

        Question question = questionMapper.getById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        QuestionDTO questionDTO = new QuestionDTO();

        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.findById(question.getCreator());

        questionDTO.setUser(user);

        return questionDTO;

    }

    @Override
    public void createOrUpdate(Question question) {
        Question dbQuestion = questionMapper.getById(question.getId());
        if (dbQuestion == null) {
            questionMapper.create(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateByQuestion(question);
        }
    }
}
