package site.crits.community.model;

import lombok.Data;

/**
 * @author Carlos
 * @description 问题
 * @Date 2019/8/11
 */

@Data
public class Question {

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
}
