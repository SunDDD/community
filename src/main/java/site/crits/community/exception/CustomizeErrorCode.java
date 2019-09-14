package site.crits.community.exception;

/**
 * 错误码
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "问题查询不到."),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复."),
    NOT_LOGIN(2003, "未登录不能进行评论，请先登录."),
    SYS_ERROR(2004, "404 Not Found."),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在."),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在."),
    ;


    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
