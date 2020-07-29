package com.hbnu.util;

import com.sun.org.apache.bcel.internal.classfile.Code;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Result <br/>
 * Description: <br/>
 * date: 2020/7/29 19:37<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
public class Result {
    @ApiModelProperty(value = "返回码")
    private int Code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据总条数")
    private long count;
    @ApiModelProperty(value = "返回数据")
    private Object data;


    private Result() {
    }

    public static Result sucess() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("操作成功！");
        return result;
    }

    public static Result failed() {
        Result result = new Result();
        result.setCode(ResultCode.FAILED);
        result.setMessage("操作失败！");
        return result;
    }

    public Result setData(Object object) {
        this.data = object;
        return this;
    }

    public Result setCount(long count) {
        this.count = count;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
}
