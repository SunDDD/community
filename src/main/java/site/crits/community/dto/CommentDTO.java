package site.crits.community.dto;

import lombok.Data;
import site.crits.community.model.User;

/**
 * @author Carlos
 * @description 评论传输
 * @Date 2019/9/15
 */

@Data
public class CommentDTO {

    private Long id;
    private Integer type;
    private Long parentId;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
