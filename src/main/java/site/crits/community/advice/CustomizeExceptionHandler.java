package site.crits.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import site.crits.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos
 * @description
 * @Date 2019/8/17
 */

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model) {

        System.out.println("CustomizeExceptionHandler.handle.ex.getMessage(): " + ex.getMessage());
        if (ex instanceof CustomizeException) {
            model.addAttribute("message", ex.getMessage());
        } else  {
            model.addAttribute("message", "404 Not Found.");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
