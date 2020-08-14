package com.hbnu.edu.client;

import com.hbnu.util.Result;
import org.springframework.stereotype.Component;

/**
 * ClassName: VodClientImpl <br/>
 * Description: <br/>  远程调用方法,熔断时调用的方法中
 * date: 2020/8/14 8:57<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class VodClientImpl implements VodClient {
    //远程调用方法失败时,会自动执行实现类中的方法 需要在接口配置注解
    @Override
    public Result deleteOneVideo(String videoid) {
        return Result.failed().setCode(20001).setMessage("删除视频失败");
    }

    @Override
    public Result test() {
        return Result.failed();
    }
}
