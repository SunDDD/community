package site.crits.community.dto;

import lombok.Data;
import site.crits.community.exception.CustomizeErrorCode;
import site.crits.community.exception.CustomizeException;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/29
 */

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message) {

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("请求成功");
        resultDTO.setCode(200);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());

    }
}
