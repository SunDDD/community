package site.crits.community.exception;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/17
 */

public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
