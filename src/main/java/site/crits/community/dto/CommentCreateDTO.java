package site.crits.community.dto;

import lombok.Data;

/**
 * @author Carlos
 * @description 评论创建
 * @Date 2019/9/15
 */

@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String content;
}
