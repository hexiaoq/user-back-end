package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.UsersBusinessEntity;
import com.wefive.goverment.service.UsersBusinessService;
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
@RequestMapping("goverment/usersbusiness")
public class UsersBusinessController {
    @Autowired
    private UsersBusinessService usersBusinessService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usersBusinessService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
		UsersBusinessEntity usersBusiness = usersBusinessService.getById(userId);

        return R.ok().put("usersBusiness", usersBusiness);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsersBusinessEntity usersBusiness){
		usersBusinessService.save(usersBusiness);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UsersBusinessEntity usersBusiness){
		usersBusinessService.updateById(usersBusiness);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] userIds){
		usersBusinessService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
