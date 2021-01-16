package com.wefive.goverment.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
@Data
@TableName("department")
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer deptId;
	/**
	 * 
	 */
	private String deptName;
	/**
	 * 
	 */
	private String location;
	/**
	 * 
	 */
	private String workTime;
	/**
	 * 
	 */
	private String description;
	private String status;
	private String picture;
	private String contact;
	private String latitude;
	private String longtitude;

	@TableField(exist = false)
	private String mintime;
}
