package site.crits.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.crits.community.dto.PaginationDTO;
import site.crits.community.mapper.QuestionMapper;
import site.crits.community.model.Question;
import site.crits.community.model.User;
import site.crits.community.provider.QuestionProvider;
import site.crits.community.service.IProfileService;

import java.util.List;

/**
 * @author Carlos
 * @description 个人页面
 * @Date 2019/8/13
 */

@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionProvider questionProvider;

    @Override
    public PaginationDTO list(Integer page, Integer size, User user) {
        Integer offset = size * (page - 1);

        PaginationDTO paginationDTO = new PaginationDTO();
//        System.out.println("ProfileServiceImpl.list: " + offset + " " + size + " " + user.getAccountId());
        List<Question> questionList = questionMapper.listU(offset, size, user.getAccountId());
        Integer totalCount = questionMapper.questionCountByUser(user.getAccountId());
//        System.out.println("P totalCount: " + totalCount);
        PaginationDTO dto = questionProvider.list(paginationDTO, questionList, page, size, totalCount);
        return dto;
    }
}
