package com.wefive.goverment.controller.ourcontroller;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wefive.goverment.common.utils.R;
import com.wefive.goverment.entity.UsersEntity;
import com.wefive.goverment.service.UsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

@RestController
public class logincontroller {
    @Resource
    UsersService usersService;
    @PostMapping("/register")
    public R reigster(@RequestBody UsersEntity user)
    {
        String phone = user.getPhone();
        String cardId = user.getCardId();
        if(usersService.getOne(new QueryWrapper<UsersEntity>().eq("phone",phone))!=null) {
            return new R().error("该手机号已经注册");
        }
        else if(usersService.getOne(new QueryWrapper<UsersEntity>().eq("card_id",cardId))!=null)
        {
            return new R().error("该身份证号已注册");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);


        System.out.println("进入注册");
        usersService.save(user);
        return new R().ok("注册成功");
    }
    @PostMapping("/update")
    public R update(@RequestBody UsersEntity user)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        UsersEntity byId = usersService.getById(user);
        try {
            usersService.updateById(user);

            String phone = user.getPhone();
            String cardId = user.getCardId();
            if(usersService.count(new QueryWrapper<UsersEntity>().eq("phone", phone))>1) {
                usersService.updateById(byId);
                return new R().error("该手机号已经注册");


            }
        } catch (Exception e) {
            return new R().error("该身份证号已经注册");
        }


        return R.ok("修改成功");
    }

    @PostMapping("/login")
    public R login(String loginId,@RequestParam(value="password") String pwd)
    {

        UsersEntity byId = usersService.getOne(new QueryWrapper<UsersEntity>().eq("phone", loginId).or().eq("card_id", loginId));
        if(byId ==null)
        {
            return new R().error("无此用户");
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(pwd,byId.getPassword()))
        {
            return new R().ok("登陆成功").put("user",byId);
        }
       else return R.error(401,"密码错误");
    }
}
