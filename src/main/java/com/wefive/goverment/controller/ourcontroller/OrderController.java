package com.wefive.goverment.controller.ourcontroller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wefive.goverment.common.utils.R;
import com.wefive.goverment.dao.UserorderDao;
import com.wefive.goverment.entity.DepartmentEntity;
import com.wefive.goverment.entity.OrderNumberEntity;
import com.wefive.goverment.entity.UserorderEntity;
import com.wefive.goverment.service.DepartmentService;
import com.wefive.goverment.service.OrderNumberService;
import com.wefive.goverment.service.UserorderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Resource
	private UserorderService userorderService;
	@Resource
	private OrderNumberService orderNumberService;
	@Resource
	private UserorderDao userorderDao;
	@Resource
	private DepartmentService departmentService;
	@PostMapping("/add")
    public R add(@RequestBody UserorderEntity userorderEntity) {
		Integer deptId=userorderEntity.getDeptId();
		Integer userId=userorderEntity.getUserId();
		if(userorderService.getOne(new QueryWrapper<UserorderEntity>().eq("dept_id", deptId).eq("user_id", userId))!=null) {
			return R.error("您已预约");
		}
		else {
			userorderService.save(userorderEntity);
			orderNumberService.addn(userorderEntity);
			//int person=orderNumberService.getOne(new QueryWrapper<OrderNumberEntity>().eq("d", val))
		}
    	return R.ok("预约成功");
    }
	@PostMapping("/alert")
    public R alert(@RequestBody UserorderEntity userorderEntity) {
		Integer deptId=userorderEntity.getDeptId();
		LocalDate orderDay=userorderEntity.getOrderDay();
		Integer orderTime=userorderEntity.getOrderTime();
		
		if(orderNumberService.getOne(new QueryWrapper<OrderNumberEntity>().eq("dept_id", deptId).eq("order_day", orderDay).eq("order_time", orderTime)) ==null) {
			return R.ok().put("nowpeople", 0);
		}
		else {
			int person=orderNumberService.getOne(new QueryWrapper<OrderNumberEntity>().eq("dept_id", deptId).eq("order_day", orderDay).eq("order_time", orderTime)).getOrderNum();
			return R.ok().put("nowpeople", person);
		}
    	
    }
	@RequestMapping("/cancel")
	public R cancel(@RequestParam("userId") int userId,@RequestParam("deptId") int deptId) {
		if(userorderService.ordercancel(userId, deptId)) {
			return R.ok("取消预约成功");
		}
		return R.error("取消预约失败");
	}
	@RequestMapping("/myorder")
	public R getmyorder(@RequestParam("userId") int userId) {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		//Map<String, Object> map=new HashMap<String, Object>();
		list.addAll(userorderService.listMaps(new QueryWrapper<UserorderEntity>().eq("user_id", userId)));
		/*for(Map<String, Object> map : list) {
			for(Map.Entry<String, Object> entry: map.entrySet()) {
				if(entry.getKey().equals("dept_id")) {
					//System.out.println(entry.getKey());
					String deptname=departmentService.getOne(new QueryWrapper<DepartmentEntity>().eq("dept_id", entry.getValue())).getDeptName();
					entry.setValue(deptname);
					
					//map.put("deptName", deptname);
				}
				System.out.println(entry);
			}
			
		}*/
		return R.ok().put("myorder", list);
	}
	@RequestMapping("name")
	public R getname(@RequestParam("deptId") int deptId) {
		String deptname=departmentService.getOne(new QueryWrapper<DepartmentEntity>().eq("dept_id", deptId)).getDeptName();
		return R.ok().put("name", deptname);
	}
}
