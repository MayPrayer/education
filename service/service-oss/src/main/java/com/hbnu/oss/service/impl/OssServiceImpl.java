package com.hbnu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.hbnu.oss.service.OssService;
import com.hbnu.oss.util.ReadPropertiesConfig;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * ClassName: OssServiceImpl <br/>
 * Description: <br/>
 * date: 2020/8/5 11:36<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Service
public class OssServiceImpl implements OssService {


    /**
     * 获取凭证，
     * 生成唯一文件名，包括地址，
     * 上传文件，
     * 返回访问路径
     **/
    @Override
    public String imageUpload(MultipartFile multipartFile)  {
        //读取凭证配置
        String endpoint = ReadPropertiesConfig.END_POINT;
        String accessKeyId = ReadPropertiesConfig.KEY_ID;
        String accessKeySecret = ReadPropertiesConfig.KEY_SECRET;
        String bucketName = ReadPropertiesConfig.BUCKET_NAME;
        //生成文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileOriName = multipartFile.getOriginalFilename();
        System.out.println("原始名称"+fileOriName);
        String suffix = fileOriName.substring(fileOriName.lastIndexOf(".") + 1);
        System.out.println("后缀名"+suffix);
        String curdate =new DateTime().toString("yyyy-MM-dd");
        //拼接上传文件名称
        String filename=uuid+"."+suffix;
        //外网访问路径
        String filepath = "https://"+bucketName+"."+endpoint+"/"+filename;
        //获取问价输入流
        InputStream inputStream =null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, curdate+"/"+filename, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();

        return filepath;
    }
}
