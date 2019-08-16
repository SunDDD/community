package site.crits.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import site.crits.community.model.User;

/**
 * @author Carlos
 * @description 插入用户信息
 * @Date 2019/8/10
 */

@Component
@Mapper
public interface UserMapper {

    @Insert("insert into community_user (account_id, name, token, gmt_create, gmt_modified, bio, avatar_url) values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from community_user where token = #{token}")
    User findByToken(String token);

    @Select("select * from community_user where id = #{id}")
    User findById(Integer id);

    @Select("select * from community_user where account_id = #{accountId}")
    User findByAccountId(String accountId);

    @Update("update community_user set name=#{name}, token=#{token}, gmt_modified=#{gmtModified}, bio=#{bio}, avatar_url=#{avatarUrl} where account_id=#{accountId}")
    void updateByUser(User user);
}
