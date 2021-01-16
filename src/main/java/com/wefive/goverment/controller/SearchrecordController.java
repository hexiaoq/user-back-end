package com.wefive.goverment.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.SearchrecordEntity;

import com.wefive.goverment.service.SearchrecordService;
import com.wefive.goverment.service.impl.SearchrecordServiceImpl;
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
@RequestMapping("goverment/searchrecord")
public class SearchrecordController {
    @Autowired
    private SearchrecordService searchrecordService;
    @Resource
    private SearchrecordService se=new SearchrecordServiceImpl();

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = searchrecordService.queryPage(params);
        
        return R.ok().put("page", page);
    }
    @RequestMapping("/top")
    public R top(@RequestParam("time") Date time) {
    	Map<String, Integer> map=new HashMap<String, Integer>();
    	map=(Map<String, Integer>) se.gettop(time);
    	return R.ok().put("top",map);
    }
    /**
     * 信息
     */
    /*@RequestMapping("/info/{busName}")
    public R info(@PathVariable("busName") String busName){
    	searchrecordService.getById(busName);
        return R.ok().put("searchrecord", busName);
    }*/

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SearchrecordEntity searchrecord){
    	searchrecordService.save(searchrecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SearchrecordEntity searchrecord){
    	searchrecordService.updateById(searchrecord);

        return R.ok();
    }

    /**
     * 删除
     */
    /*@RequestMapping("/delete")
    public R delete(@RequestBody Integer[] userIds){
    	searchrecordService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }*/

}
