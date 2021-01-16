package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.DepartmentEntity;
import com.wefive.goverment.service.DepartmentService;
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
@RequestMapping("goverment/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = departmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    public R info(@PathVariable("deptId") Integer deptId){
		DepartmentEntity department = departmentService.getById(deptId);

        return R.ok().put("department", department);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DepartmentEntity department){
		departmentService.save(department);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DepartmentEntity department){
		departmentService.updateById(department);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] deptIds){
		departmentService.removeByIds(Arrays.asList(deptIds));

        return R.ok();
    }

}
