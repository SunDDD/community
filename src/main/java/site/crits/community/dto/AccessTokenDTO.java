package site.crits.community.dto;

import lombok.Data;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/10
 */

@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
