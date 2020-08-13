package com.hbnu.edu.mapper;

import com.hbnu.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbnu.edu.entity.vo.FinalCourseInfo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
public interface CourseMapper extends BaseMapper<Course> {

    public FinalCourseInfo getFinalCousrseInfo(String courseid);

}
