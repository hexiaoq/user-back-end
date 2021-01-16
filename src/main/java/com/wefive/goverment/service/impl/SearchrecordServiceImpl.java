package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.SearchrecordDao;
import com.wefive.goverment.entity.SearchrecordEntity;

import com.wefive.goverment.service.SearchrecordService;


@Service("searchrecordService")
public class SearchrecordServiceImpl extends ServiceImpl<SearchrecordDao, SearchrecordEntity> implements SearchrecordService {
	@Resource
	private SearchrecordDao searchrecordDao;
	@Resource
	private SearchrecordEntity searchrecordEntity;
	//@Resource
	//private SearchrecordService searchrecordService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SearchrecordEntity> page = this.page(
                new Query<SearchrecordEntity>().getPage(params),
                new QueryWrapper<SearchrecordEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public boolean saverecord(SearchrecordEntity searchrecordEntity) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(searchrecordEntity.getBusName());
			searchrecordDao.insert(searchrecordEntity);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, Integer> gettop(Date time) {
		// TODO Auto-generated method stub
		List<SearchrecordEntity> l1=new ArrayList<SearchrecordEntity>();
		List<String> l2=new ArrayList<String>();
		Map<String, Integer> map=new HashMap<String, Integer>();
		JSONObject json=new JSONObject();
		Calendar cal=Calendar.getInstance();        
    	cal.setTime(time);
    	int week=cal.get(Calendar.DAY_OF_WEEK);
    	//System.out.println(week);
    	l1.addAll(searchrecordDao.selectList(new QueryWrapper<SearchrecordEntity>().select("bus_name","search_time")));
    	//l1.addAll(searchrecordService.listMaps(new QueryWrapper<SearchrecordEntity>().select("bus_name","search_time")));
    	for(SearchrecordEntity obj : l1) {
    		//System.out.println(obj.getSearchTime().getDay()+""+obj.getBusName());
    		Calendar calendar=Calendar.getInstance();
    		calendar.setTime(obj.getSearchTime());
    		if(calendar.get(Calendar.DAY_OF_WEEK) == week) {
    			if(!map.isEmpty()&&map.keySet().contains(obj.getBusName())) {
    				for(Map.Entry<String, Integer> m: map.entrySet()) {
        				if(m.getKey().equals(obj.getBusName())) {
        					int count=m.getValue();
        					//System.out.println(count++);
        					map.put(obj.getBusName(), ++count);
        					//System.out.println(m.getValue());
        				}
        			}
    			}
    			else {
    				map.put(obj.getBusName(), 1);
    			}
    			l2.add(obj.getBusName());
    			//System.out.println(obj.getBusName());
    		}
    		
    	}
    	//System.out.println(sortMap(map));
    	/*if(!map.isEmpty()) {
    		for(Map.Entry<String, Integer> m: map.entrySet()) {
    			System.out.println("K: "+m.getKey()+",V: "+m.getValue());
    		}
    	}*/
    	return sortMap(map);
		//return null;
	}


public  LinkedHashMap<String, Integer> sortMap(Map<String, Integer> map){
	class MapClass{															//自定义类保存键值对
		private String key;
		private int value;
		
		public MapClass(String key, int value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public int getValue() {
			return value;
		}
		
		
	}
	class MapSortMethod implements Comparator<MapClass>{					//为自定义类实现排序方法
		@Override
		public int compare(MapClass o1, MapClass o2) {
			//int result = Integer.compare(o1.getValue(), o2.getValue());		//按值大小升序排列
			int result = Integer.compare(o2.getValue(), o1.getValue());	//按值大小降序排列	
			if(result != 0)
				return result;		
			return o1.getKey().compareTo(o2.getKey());						//值相同时按键字典顺序排列
		}
	}
	
	ArrayList<MapClass> mapclass = new ArrayList<MapClass>();				//以ArrayList保存自定义类
	for(String k: map.keySet())
		mapclass.add(new MapClass(k, map.get(k)));
	Collections.sort(mapclass, new MapSortMethod());						//使用Collections.sort()方法，第二个参数为自定义排序方法，需要实现Comparator接口
					
	LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	for(MapClass m: mapclass)
		sortedMap.put(m.getKey(), m.getValue());
	return sortedMap;														//用LinkedHashMap返回排好序的Map
}
}

