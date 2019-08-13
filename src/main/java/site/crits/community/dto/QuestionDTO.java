package site.crits.community.dto;

import lombok.Data;
import site.crits.community.model.User;

/**
 * @author Carlos
 * @description 问题信息加头像信息
 * @Date 2019/8/12
 */

@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private String accountId;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;

}
