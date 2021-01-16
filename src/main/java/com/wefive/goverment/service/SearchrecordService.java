package com.wefive.goverment.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.entity.SearchrecordEntity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
public interface SearchrecordService extends IService<SearchrecordEntity> {
	//List<Map<String,Object>> Getsearchlist(int userid);
	boolean saverecord(SearchrecordEntity searchrecordEntity);
    PageUtils queryPage(Map<String, Object> params);
    Map<String, Integer> gettop(Date time);
}

