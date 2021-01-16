package com.wefive.goverment.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.entity.SearchEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
public interface SearchService extends IService<SearchEntity> {
	List<Map<String,Object>> Getsearchlist(int userid);
    PageUtils queryPage(Map<String, Object> params);
    List<Map<String, Object>> getdepartwithbus(String info);
    List<Map<String, Object>> getdepart(String info);
    String mainfun(String info) throws IOException ;
    //String getToken(String region,String username,String password,String userName);
    
}

