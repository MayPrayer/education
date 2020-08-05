package com.hbnu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: OssService <br/>
 * Description: <br/>
 * date: 2020/8/5 11:35<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface OssService {
    String imageUpload(MultipartFile multipartFile);
}
