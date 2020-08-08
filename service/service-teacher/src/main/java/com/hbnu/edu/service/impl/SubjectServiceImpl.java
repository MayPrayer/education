package com.hbnu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.hbnu.edu.entity.Subject;
import com.hbnu.edu.excel.SubjectData;
import com.hbnu.edu.listener.SubjectExcelListener;
import com.hbnu.edu.mapper.SubjectMapper;
import com.hbnu.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-08
 */
@Service
@Slf4j
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void addSubject(MultipartFile file ,SubjectService subjectService) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        }catch (IOException e){
           log.error("获取文件输入流失败！");
        }

        EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
    }
}
