package site.crits.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import site.crits.community.model.Question;

/**
 * @author Carlos
 * @description 问题
 * @Date 2019/8/11
 */

@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into community_question (title, description, gmt_create, gmt_modified, creator, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

}
