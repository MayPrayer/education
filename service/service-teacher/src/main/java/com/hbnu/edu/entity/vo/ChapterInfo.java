package com.hbnu.edu.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hbnu.edu.entity.Video;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Chapter <br/>
 * Description: <br/>
 * date: 2020/8/10 15:55<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
public class ChapterInfo implements Serializable {
    @ApiModelProperty(value = "章节ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    List<VideoInfo> videos = new ArrayList<>();
}
