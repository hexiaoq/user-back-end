package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.DeptBusinessEntity;
import com.wefive.goverment.service.DeptBusinessService;
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
@RequestMapping("goverment/deptbusiness")
public class DeptBusinessController {
    @Autowired
    private DeptBusinessService deptBusinessService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deptBusinessService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    public R info(@PathVariable("deptId") Integer deptId){
		DeptBusinessEntity deptBusiness = deptBusinessService.getById(deptId);

        return R.ok().put("deptBusiness", deptBusiness);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DeptBusinessEntity deptBusiness){
		deptBusinessService.save(deptBusiness);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DeptBusinessEntity deptBusiness){
		deptBusinessService.updateById(deptBusiness);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] deptIds){
		deptBusinessService.removeByIds(Arrays.asList(deptIds));

        return R.ok();
    }

}
