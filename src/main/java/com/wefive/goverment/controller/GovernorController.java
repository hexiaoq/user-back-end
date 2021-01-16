package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.GovernorEntity;
import com.wefive.goverment.service.GovernorService;
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
@RequestMapping("goverment/governor")
public class GovernorController {
    @Autowired
    private GovernorService governorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = governorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{goverId}")
    public R info(@PathVariable("goverId") Integer goverId){
		GovernorEntity governor = governorService.getById(goverId);

        return R.ok().put("governor", governor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GovernorEntity governor){
		governorService.save(governor);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GovernorEntity governor){
		governorService.updateById(governor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] goverIds){
		governorService.removeByIds(Arrays.asList(goverIds));

        return R.ok();
    }

}
