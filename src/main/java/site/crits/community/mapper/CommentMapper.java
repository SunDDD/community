package site.crits.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import site.crits.community.model.Comment;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    @Insert("insert into community_comment (type, parent_id, gmt_create, gmt_modified, commentator, like_count, content) values (#{type}, #{parentId}, #{gmtCreate}, #{gmtModified}, #{commentator}, #{likeCount}, #{content})")
    void insert(Comment comment);

    @Select("select * from community_comment where id = #{id}")
    Comment selectById(Long id);

    @Select("select * from community_comment where parent_id = #{id}")
    List<Comment> selectByParentId(Long id);



}
