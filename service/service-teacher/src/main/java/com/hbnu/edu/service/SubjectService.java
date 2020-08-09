package com.hbnu.edu.service;

import com.hbnu.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbnu.edu.entity.subject.FirstTitle;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-08
 */
public interface SubjectService extends IService<Subject> {

    void addSubject(MultipartFile file,SubjectService subjectService);

    List<FirstTitle> getAllSubject();
}
