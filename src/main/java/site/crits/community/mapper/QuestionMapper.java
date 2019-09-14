package site.crits.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Insert("insert into community_question (title, description, gmt_create, gmt_modified, creator, account_id, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{accountId}, #{tag})")
    void create(Question question);

    @Select("select * from community_question order by id desc limit  #{offset}, #{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select * from community_question where account_id = #{id} order by id desc limit  #{offset}, #{size}")
    List<Question> listU(Integer offset, Integer size, String id);

    @Select("select count(1) from community_question")
    Integer questionCount();

    @Select("select count(1) from community_question where account_id = #{accountId}")
    Integer questionCountByUser(String accountId);

    @Select("select * from community_question where id = #{id}")
    Question getById(Integer id);

    @Update("update community_question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    void updateByQuestion(Question question);

    @Update("update community_question set view_count = #{viewCount} where id = #{id}")
    void setViewCount(Integer id, Integer viewCount);

    @Update("update community_question set comment_count = #{commentCount} where id = #{id}")
    void setCommentCount(Integer id, Integer commentCount);
}
