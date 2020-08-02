package com.hbnu.base.config.exception;

import com.hbnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GobalExceptionHandler <br/>
 * Description: <br/>
 * date: 2020/7/29 22:14<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@ControllerAdvice
@Slf4j //添加日志注解，使用log.error 在日志中显示异常信息
public class GlobalExceptionHandler {

    /*出现异常后会自动调用下面的方法，并且传入当前异常对象*/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.failed().setMessage("执行了全局异常");

    }



    /*自定义异常*/
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result myExceptionHandler(MyException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.failed().setCode(e.getErrorCode()).setMessage(e.getMessage());

    }
}
