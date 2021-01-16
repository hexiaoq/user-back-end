package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.MaterialEntity;
import com.wefive.goverment.service.MaterialService;
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
@RequestMapping("goverment/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{materialId}")
    public R info(@PathVariable("materialId") Integer materialId){
		MaterialEntity material = materialService.getById(materialId);

        return R.ok().put("material", material);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MaterialEntity material){
		materialService.save(material);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MaterialEntity material){
		materialService.updateById(material);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] materialIds){
		materialService.removeByIds(Arrays.asList(materialIds));

        return R.ok();
    }

}
