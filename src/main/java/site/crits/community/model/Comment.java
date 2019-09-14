package site.crits.community.model;

import lombok.Data;

/**
 * @author Carlos
 * @description 回复
 * @Date 2019/8/29
 */

@Data
public class Comment {

    private Integer type;
    private Long parentId;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;

}
