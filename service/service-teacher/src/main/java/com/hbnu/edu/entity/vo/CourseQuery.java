package com.hbnu.edu.entity.vo;

import com.hbnu.edu.entity.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: CourseQuery <br/>
 * Description: <br/>
 * date: 2020/8/12 17:16<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "状态 Draft未发布 Normal已发布")
    private String status;
}
