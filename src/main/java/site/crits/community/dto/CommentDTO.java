package site.crits.community.dto;

import lombok.Data;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/29
 */

@Data
public class CommentDTO {

    private Long parentId;
    private String content;
    private Integer type;

}
