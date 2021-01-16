package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.UsersEntity;
import com.wefive.goverment.service.UsersService;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.common.utils.R;



/**
 * 
 *
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
@RestController
@RequestMapping("goverment/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 获得用户个人信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
		UsersEntity users = usersService.getById(userId);

        return R.ok().put("users", users);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsersEntity users){
		usersService.save(users);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UsersEntity users){
		usersService.updateById(users);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] userIds){
		usersService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
