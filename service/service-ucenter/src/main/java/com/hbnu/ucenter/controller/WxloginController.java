package com.hbnu.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.hbnu.ucenter.entity.Member;
import com.hbnu.ucenter.entity.vo.RegisterVo;
import com.hbnu.ucenter.service.MemberService;
import com.hbnu.ucenter.util.HttpClientUtils;
import com.hbnu.ucenter.util.ReadPropertiesConfig;
import com.hbnu.util.JwtUtil;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname WxloginController
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/17 10:49
 */

@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
@Slf4j
public class WxloginController {
    @Autowired
    private MemberService memberService;




    //微信登录开始
    @ApiOperation("生成微信扫描二维码")
    @GetMapping("login")
    public String makecode() {
        //请求重定向至微信
        //对url编码
        String redirect_url = null;
        try {
            redirect_url = URLEncoder.encode(ReadPropertiesConfig.REDIRCT_URL, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //拼接   ,微信接口url 参数appid +经过url编码的appredirectUrl +响应类型一般为code +scope类型（网页为snsapi_login）+state自定已参数
        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + ReadPropertiesConfig.KEY_ID + "&redirect_uri=" + redirect_url
                + "&response_type=code&scope=snsapi_login&state=atguigu#wechat_redirect";

        return "redirect:" + url;
    }


    //第二步，请求openid 和token
    @ApiOperation("微信返回数据")
    @GetMapping("callback")
    public String callback( String code,  String state) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + ReadPropertiesConfig.KEY_ID +
                "&secret=" + ReadPropertiesConfig.KEY_SECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        //时使用httpclient 发出请求  ，接收数据
        String result = null;
        try {
            result = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将获取的返回报文 转换成hashmap
        Gson gson = new Gson();
        HashMap hashMap = gson.fromJson(result, HashMap.class);
        String openid = (String) hashMap.get("openid");
        String access_token = (String) hashMap.get("access_token");

        //根据第二步中的信息到第三步获取用户信息
        String infourl = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=" + access_token +
                "&openid=" + openid;
        String userinfo = null;
        try {
            userinfo = HttpClientUtils.get(infourl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //转换成hashmap
        Map userinfoMap = new Gson().fromJson(userinfo, HashMap.class);

        //如果数据库中已经存在微信号，就不保存，直接登录
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper();
        memberQueryWrapper.eq("openid", openid);
        int count = memberService.count(memberQueryWrapper);
        Member member = null;
        if (count > 0) {
            //直接登录
            member = memberService.getOne(memberQueryWrapper);
        } else {
            // 保存之后再登陆
            member = new Member();
            member.setAvatar((String) userinfoMap.get("headimgurl"));
            member.setNickname((String) userinfoMap.get("nickname"));
            member.setOpenid(openid);
            memberService.save(member);
        }
        //封装token信息
        String token = JwtUtil.getJwtToken(member.getId(), member.getNickname());
        return "redirect:http://localhost:3000?token=" + token;
    }


}
