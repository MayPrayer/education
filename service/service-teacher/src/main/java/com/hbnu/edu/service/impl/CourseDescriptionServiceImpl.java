package com.hbnu.edu.service.impl;

import com.hbnu.edu.entity.CourseDescription;
import com.hbnu.edu.mapper.CourseDescriptionMapper;
import com.hbnu.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {


    @Override
    public void saveOneCourseDesc(CourseDescription courseDescription){
        this.save(courseDescription);
    }

    @Override
    public CourseDescription selectDescByCId(String courseid) {
       return this.getById(courseid);
    }

    @Override
    public Boolean UpdateCourse(CourseDescription courseDescription) {
       boolean result =  this.saveOrUpdate(courseDescription);
        return result;
    }


}
