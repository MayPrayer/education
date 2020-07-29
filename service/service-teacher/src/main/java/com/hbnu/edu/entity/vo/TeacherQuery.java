package com.hbnu.edu.entity.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: TeacherQuery <br/>
 * Description: <br/> 封装老师查询条件成类
 * date: 2020/7/29 20:52<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师姓名模糊查询")
    private String name;

    @ApiModelProperty(value = "教师等级")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
