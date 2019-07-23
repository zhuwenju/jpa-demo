
package com.frame.base.config;

import com.frame.base.common.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.jk.ocr.config
 *
 * @author lise
 * @comment
 * @date 2019-01-13 01:26
 */
@ ControllerAdvice
public class GlobalExceptionHandler
{
    /**
     * 根据业务规则,统一处理异常。
     */
    @ ExceptionHandler(Exception.class)
    @ ResponseBody
    @ ResponseStatus(HttpStatus.OK)
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse res, Exception e)
    {
        e.printStackTrace();
        res.setStatus(HttpStatus.OK.value());
        return JsonResult.failure(e.getMessage());
    }

}
