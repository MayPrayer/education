package com.hbnu.ucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname RegisterVo
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/15 19:14
 */
@Data
public class RegisterVo {
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "验证码")
    private String vifcode;

}
