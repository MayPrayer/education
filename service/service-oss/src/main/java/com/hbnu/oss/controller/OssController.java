package com.hbnu.oss.controller;

import com.hbnu.oss.service.OssService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: OssController <br/>
 * Description: <br/>
 * date: 2020/8/5 11:37<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@RestController
@RequestMapping("edo/oss")
@CrossOrigin
@Slf4j
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传，返回文件路径")
    @PostMapping("fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        log.info("上传了一个文件");
        String filepath = ossService.imageUpload(multipartFile);
        log.info("返回路径为" + filepath);
        Map map = new HashMap();
        map.put("filepath", filepath);
        return Result.sucess().setData(map);
    }
}
