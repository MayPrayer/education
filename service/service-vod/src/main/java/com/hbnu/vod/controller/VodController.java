package com.hbnu.vod.controller;

import com.hbnu.base.config.exception.MyException;
import com.hbnu.util.Result;
import com.hbnu.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: VodController <br/>
 * Description: <br/>
 * date: 2020/8/13 10:55<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@RestController
@Controller
@CrossOrigin
@RequestMapping("edd/vod")
@Slf4j
public class VodController {
    @Autowired
    private VodService vodService;

    @ApiOperation(value = "上传视频，返回视频id")
    @PostMapping("upload")
    public Result uploadVideo(MultipartFile file) {
        String id = vodService.uploadVideoAly(file);
        return Result.sucess().setData(id);
    }

    @ApiOperation(value = "删除视频")
    @DeleteMapping("{videoid}")
    public Result deleteOneVideo(@PathVariable("videoid") String videoid) {
        log.error("视频删除被远程调用过");
        try {
            vodService.removeAlyVideo(videoid);
        } catch (MyException e) {
            return Result.failed().setData("文件删除失败");
        }
        return Result.sucess();
    }

    @ApiOperation(value = "批量删除视频")
    @DeleteMapping("deletesomevdo")
    public Result deleteSomeVideo(@RequestBody List delList) {
        try {
            vodService.removeMoreAlyVideo(delList);
        } catch (MyException e) {
            return Result.failed().setData("文件批量删除失败");
        }
        return Result.sucess();
    }

    @ApiOperation(value = "测试远程调用")
    @GetMapping("test")
    public Result test(){
        log.error("我被远程调用了");
        return Result.sucess();
    }
}
