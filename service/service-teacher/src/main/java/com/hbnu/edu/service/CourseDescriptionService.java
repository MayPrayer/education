package com.hbnu.edu.service;

import com.hbnu.edu.entity.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
public interface CourseDescriptionService extends IService<CourseDescription> {
     void saveOneCourseDesc(CourseDescription courseDescription);
     CourseDescription selectDescByCId(String courseid);

    Boolean UpdateCourse(CourseDescription courseDescription);
}
