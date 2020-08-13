package com.hbnu.vod.service;

import com.hbnu.base.config.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: VodService <br/>
 * Description: <br/>
 * date: 2020/8/13 10:02<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List videoIdList) throws MyException;

    //根据视频id删除阿里云视频
    void removeAlyVideo(String id) throws MyException;
}
