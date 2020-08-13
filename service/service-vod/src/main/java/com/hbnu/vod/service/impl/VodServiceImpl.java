package com.hbnu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.hbnu.base.config.exception.MyException;
import com.hbnu.vod.service.VodService;
import com.hbnu.vod.util.InitVodClient;
import com.hbnu.vod.util.ReadPropertiesConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * ClassName: VodServiceImpl <br/>
 * Description: <br/>
 * date: 2020/8/13 10:02<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Service
public class VodServiceImpl implements VodService {

    //初始化，获取配置信息
    public DefaultAcsClient init() {
        String kid = ReadPropertiesConfig.KEY_ID;
        String kst = ReadPropertiesConfig.KEY_SECRET;
        DefaultAcsClient client = InitVodClient.initVodClient(kid, kst);
        return client;
    }

    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            //accessKeyId, accessKeySecret
            //fileName：上传文件原始名称
            // 01.03.09.mp4
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ReadPropertiesConfig.KEY_ID, ReadPropertiesConfig.KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeMoreAlyVideo(List videoIdList) throws MyException {
        //需要凭证信息才能操作（删除）视频
        try {
            //初始化对象
            DefaultAcsClient client = init();
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList转换成1，2，3形式
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException( "删除视频失败",30000);
        }
    }


    @Override
    public void removeAlyVideo(String id) throws MyException {
        try {
            //初始化对象
          DefaultAcsClient client = init();
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException( "删除视频失败",30000);
        }
    }


}
