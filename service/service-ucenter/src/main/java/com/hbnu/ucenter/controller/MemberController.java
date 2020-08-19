package com.hbnu.ucenter.controller;


import com.hbnu.ucenter.entity.Member;
import com.hbnu.ucenter.entity.vo.RegisterVo;
import com.hbnu.ucenter.service.MemberService;
import com.hbnu.util.JwtUtil;
import com.hbnu.util.Result;
import com.hbnu.util.ordervo.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-15
 */
@RestController
@CrossOrigin
@RequestMapping("/ucenter/member")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;

    @ApiOperation("会员登录")
    @PostMapping("login")
    public Result ogin(@RequestBody Member member) {
        log.error("执行了登录");
        String token = memberService.login(member);
        if (StringUtils.isEmpty(token)) {
            return Result.failed();
        } else {
            return Result.sucess().setData(token);
        }
    }

    @ApiOperation("会员注册")
    @PostMapping("register")
    public Result register(@RequestBody RegisterVo register) {
        log.error("执行了注册");
        Boolean result = memberService.register(register);
        return result ? Result.sucess() : Result.failed();
    }

    @ApiOperation("解析token数据")
    @GetMapping("parsetoken")
    public Result parseToken(HttpServletRequest request) {
        String id = JwtUtil.getMemberIdByJwtToken(request);

        Member userinfo = memberService.parseToken(id);
        if (userinfo != null) {
            return Result.sucess().setData(userinfo);
        } else {
            return Result.failed();
        }
    }


    //会被远程调用
    @ApiOperation("根据用户id获取用户信息")
    @GetMapping("getMemInfo/{memid}")
    public UcenterMemberOrder getMemInfo(@PathVariable String memid) {
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        Member member = memberService.getById(memid);
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }


    //查询某一天的注册人数
    @ApiOperation("查询某一天的注册人数")
    @GetMapping("countRegister/{day}")
    public Result countRegister(@PathVariable String day){
        Integer count = memberService.countRegisterDay(day);
        return  Result.sucess().setData(count);
    }

}

