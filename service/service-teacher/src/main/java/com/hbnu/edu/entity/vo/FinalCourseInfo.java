package com.hbnu.edu.entity.vo;

/**
 * ClassName: FinalCourseInfo <br/>
 * Description: <br/>
 * date: 2020/8/12 10:27<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */

import lombok.Data;

@Data

public class FinalCourseInfo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
