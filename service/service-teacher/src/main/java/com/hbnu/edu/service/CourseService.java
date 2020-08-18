package com.hbnu.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbnu.base.config.exception.MyException;
import com.hbnu.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbnu.edu.entity.fontvo.CourseFrontVo;
import com.hbnu.edu.entity.fontvo.CourseWebVo;
import com.hbnu.edu.entity.vo.CourseInfo;
import com.hbnu.edu.entity.vo.FinalCourseInfo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
public interface CourseService extends IService<Course> {

    String addOneCourse(CourseInfo courseInfo);

    CourseInfo findCourseByCId(String courseid);

    Boolean UpdateCourse(CourseInfo courseInfo);

    FinalCourseInfo getAllFinalCourseInfo(String courseid);

//    void removeCourse(String courseId) throws MyException;

    void updateBuyCountById(String id);

    Map<String, Object> getCourseFrontList(Page<Course> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
