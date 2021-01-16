package com.wefive.goverment.controller;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.wefive.goverment.service.impl.GpsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.common.utils.R;
import com.wefive.goverment.entity.*;
import com.wefive.goverment.service.*;
import com.wefive.goverment.service.impl.SearchServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 
 *
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
@RestController
@RequestMapping("goverment/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Resource
    private UsersService usersService;
    @Resource
    private SearchService SSe=new SearchServiceImpl();
    private Getapi getapi=new Getapi();
    @Resource
    private DepartmentService departmentService;
    //private GetDepart getDepart=new GetDepart();
    @Resource
    private BusinessService businessService;
    @Resource
    private DeptBusinessService deptBusinessService;
    @Resource
    private SearchrecordService searchrecordService;
    @Resource
    private SearchrecordEntity searchrecordEntity;
    @Resource
    private MaterialService materialService;
    @Autowired
    GpsServiceImpl gpsService;
    //@RequestMapping("/")
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = searchService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/mysearchlist")
    public R list(@RequestParam("userId") int userid) {
    	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	list=SSe.Getsearchlist(userid);
    	return R.ok().put("history",list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestBody SearchEntity search){
		//SearchEntity search = searchService.getById(searchId);
		Integer userId=search.getUserId();
		String info=search.getInfo();
		
		Date time=search.getCreateTime();
		String serviceString ="test";
		List<Map<String, Object>> l;
		if(usersService.getOne(new QueryWrapper<UsersEntity>().eq("user_id",userId).last("limit 1"))==null) {
			return R.error("error");
		}
		else {
			try {
				serviceString=SSe.mainfun(info);
				//System.out.println(serviceString+"test");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(serviceString.equals("业务")) {
				List<Map<String, Object>> json=SSe.getdepartwithbus(info);
                ArrayList<DepartmentEntity> getdepbyduration = gpsService.getdepbyduration(json);

				if(!json.isEmpty()) {
					String busnameString=businessService.getOne(new QueryWrapper<BusinessEntity>().like("bus_name", info).last("limit 1")).getBusName();
				    SearchrecordEntity searchrecordEntity=new SearchrecordEntity();
				    searchrecordEntity.setBusName(busnameString);
				    searchrecordEntity.setSearchTime(time);
				    searchrecordService.saverecord(searchrecordEntity);
					save(search);
					return R.ok().put("departments",getdepbyduration);
				}
				else {
					return R.ok().put("departments", null);
				}
			}
			else {
				l=SSe.getdepart(info);
                ArrayList<DepartmentEntity> getdepbyduration = gpsService.getdepbyduration(l);
                if(!l.isEmpty()) {
					save(search);
					///searchService.save(search);
					return R.ok().put("departments", getdepbyduration);
				}
				else {
					return R.ok().put("departments", null);
				}
				
			}
			//return R.ok().put("search", search);
		}
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SearchEntity search){
		searchService.save(search);
		
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SearchEntity search){
		searchService.updateById(search);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] searchIds){
		searchService.removeByIds(Arrays.asList(searchIds));

        return R.ok();
    }
}
