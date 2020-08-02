package com.hbnu.base.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName: MyException <br/>
 * Description: <br/>
 * date: 2020/7/30 10:31<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
public class MyException extends Exception {

    private String message;
    private Integer errorCode;


}
