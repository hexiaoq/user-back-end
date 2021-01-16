package com.wefive.goverment.entity;

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
 * @date 2020-12-07 10:48:45
 */
@Data
@TableName("business")
public class BusinessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer busId;
	/**
	 * 
	 */
	private String busName;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private String requirement;
	/**
	 * 
	 */
	private Double cost;
	private int deptId;

}
