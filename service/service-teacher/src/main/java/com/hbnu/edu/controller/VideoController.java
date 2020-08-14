package com.hbnu.edu.controller;


import com.hbnu.edu.client.VodClient;
import com.hbnu.edu.entity.Video;
import com.hbnu.edu.service.VideoService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.IfNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
@Slf4j
public class VideoController {
    @Autowired
    private VideoService videoService;

    //注入vodClient，远程调用方法
    @Autowired
    private VodClient vodClient;

    @ApiOperation(value = "添加小结")
    @PostMapping("addvideo")
    public Result addVideo(@RequestBody Video video) {
        boolean result = videoService.save(video);
        return result ? Result.sucess() : Result.failed();
    }

    @ApiOperation(value = "修改小结")
    @PostMapping("updatevideo")
    public Result updateVideo(@RequestBody Video video) {
        boolean result = videoService.saveOrUpdate(video);
        return result ? Result.sucess() : Result.failed();
    }

    @ApiOperation(value = "删除小结")
    @DeleteMapping("{videoid}")
    public Result deleteVideo(@PathVariable String videoid) {
        //根据id查询小结信息
        Video video = videoService.getById(videoid);
        String sourceid = video.getVideoSourceId();
        //存在视频则删除
        Result result1 =null;
        boolean result2 =false;
        if (!StringUtils.isEmpty(sourceid)) {
            //调用远程服务删除
             result1 = vodClient.deleteOneVideo(sourceid);
        }
        if (result1.getCode().equals("20000")){
            result2 =true;
        }else{
            result2 =false;
        }
        //最后总是要删除小结信息
        boolean result3 = videoService.removeById(videoid);

        return result2&&result3 ? Result.sucess() : Result.failed();
    }


    @ApiOperation(value = "根据id查询当前小结信息")
    @GetMapping("{videoid}")
    public Result getVideo(@PathVariable String videoid) {
        Video video = videoService.getById(videoid);
        return Result.sucess().setData(video);

    }
}

