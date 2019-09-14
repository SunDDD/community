package site.crits.community.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import site.crits.community.dto.ResultDTO;
import site.crits.community.exception.CustomizeErrorCode;
import site.crits.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Carlos
 * @description
 * @Date 2019/9/14
 */

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //返回JSON
            ResultDTO resultDTO = null;
            if (e instanceof CustomizeException) {
                resultDTO =  ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {

            }
            return null;
        } else {
            //跳转错误页面
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
