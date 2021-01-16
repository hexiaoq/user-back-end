package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.BusinessEntity;
import com.wefive.goverment.service.BusinessService;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.common.utils.R;



/**
 * 
 *
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:45
 */
@RestController
@RequestMapping("goverment/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = businessService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{busId}")
    public R info(@PathVariable("busId") Integer busId){
		BusinessEntity business = businessService.getById(busId);

        return R.ok().put("business", business);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BusinessEntity business){
		businessService.save(business);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BusinessEntity business){
		businessService.updateById(business);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] busIds){
		businessService.removeByIds(Arrays.asList(busIds));

        return R.ok();
    }

}
