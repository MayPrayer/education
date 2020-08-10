package com.hbnu.edu.service.impl;

import com.hbnu.edu.entity.Course;
import com.hbnu.edu.entity.CourseDescription;
import com.hbnu.edu.entity.vo.CourseInfo;
import com.hbnu.edu.mapper.CourseMapper;
import com.hbnu.edu.service.CourseDescriptionService;
import com.hbnu.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
@Service
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Override
    public String addOneCourse(CourseInfo courseInfo) {
        //创建课程对象，并赋值 ,保存入课程表
        Course course = new Course();  //创建对象的时候就已经自动生成id了
        BeanUtils.copyProperties(courseInfo, course);
        this.save(course);
        //创建课程简介对象并赋值
        String course_id = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course_id);
        courseDescription.setDescription(courseInfo.getDescription());
        log.info("id为{}", courseDescription.getId());
        //保存入两张表
        courseDescriptionService.saveOneCourseDesc(courseDescription);
        return course_id;
    }

    @Override
    public CourseInfo findCourseByCId(String courseid) {
        Course course = this.getById(courseid);
        CourseDescription courseDescription = courseDescriptionService.selectDescByCId(courseid);
        //创建返回VO类对象
        CourseInfo courseInfo = new CourseInfo();
        //封装数据
        BeanUtils.copyProperties(course,courseInfo);
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    @Override
    public Boolean UpdateCourse(CourseInfo courseInfo) {
        //将vo对象转换成po对象
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo,course);
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfo,courseDescription);
        //保存至数据库
        Boolean result1 = this.saveOrUpdate(course);
        Boolean result2 =courseDescriptionService.UpdateCourse(courseDescription);
        return result1&&result2?true:false;
    }
}
