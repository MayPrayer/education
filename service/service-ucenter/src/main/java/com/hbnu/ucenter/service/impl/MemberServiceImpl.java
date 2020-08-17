package com.hbnu.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.ucenter.entity.Member;
import com.hbnu.ucenter.entity.vo.RegisterVo;
import com.hbnu.ucenter.mapper.MemberMapper;
import com.hbnu.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbnu.util.JwtUtil;
import com.hbnu.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-15
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(Member member) {
        System.out.println("执行了service方法");

        //1.获取登录的信息
        String inputMobile = member.getMobile();
        String inputPassword = member.getPassword();

        log.error("加密密码为{}" + MD5.encrypt(inputPassword));


        //2.如果两者有一个为空，则直接返回错误信息
        if (StringUtils.isEmpty(inputMobile) || StringUtils.isEmpty(inputPassword)) {
            return "";
        }
        //3.如果在账户中查不出对应手机号，也返回空字符串
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper();
        memberQueryWrapper.eq("mobile", inputMobile);
        Member memberquey = baseMapper.selectOne(memberQueryWrapper);
        if (null == memberquey) {
            return "";
        }
        //todo 判断是否是黑名单用户
        //4.有手机号，现在要比较密码是否一致

        if (!MD5.encrypt(inputPassword).equals(memberquey.getPassword())) {
            return "";
        } else {
            return JwtUtil.getJwtToken(memberquey.getId(), memberquey.getNickname());
        }
    }

    @Override
    public Boolean register(RegisterVo register) {
        //1.获取注册信息
        String mobile = register.getMobile();
        String nickname = register.getNickname();
        String password = register.getPassword();
        String vifcode = register.getVifcode();

        //2，判断是否为空值 ,有一个空值都返回false
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password) || StringUtils.isEmpty(vifcode)) {
            return false;
        }
        //3。判断验证码是否正确
        String code = redisTemplate.opsForValue().get(mobile);
        if (StringUtils.isEmpty(code) || !vifcode.equals(code)) {
            return false;
        }
        //4.判断库中是是否已经存在手机号，已注册用户不能重复注册
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper();
        memberQueryWrapper.eq("mobile", mobile);
        if (baseMapper.selectCount(memberQueryWrapper) > 0) {
            return false;
        } else {
            //封装实提信息
            Member member = new Member();
            BeanUtils.copyProperties(register, member);
            member.setPassword(MD5.encrypt(password));
            //设置默认头像
            member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3VCaNs9dk92ZbRkmXckpJiaehiarXzwOcHM1nlzITt8L1sdRNOYqx3DQmicQ2icIGtyaxIwqDx3O0cQ/132");
            //保存新注册用户信息
            baseMapper.insert(member);
            return true;
        }
    }

    @Override
    public Member parseToken(String id) {
        //根据手机号查询用户信息
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper();
        memberQueryWrapper.eq("id", id);
        return baseMapper.selectOne(memberQueryWrapper);
    }
}
