package site.crits.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import site.crits.community.model.Question;

import java.util.List;

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

    @Select("select * from community_question order by id desc limit  #{offset}, #{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from community_question")
    Integer questionCount();
}
