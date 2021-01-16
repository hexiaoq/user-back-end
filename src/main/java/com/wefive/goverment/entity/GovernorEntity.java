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
 * @date 2020-12-07 10:48:44
 */
@Data
@TableName("governor")
public class GovernorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer goverId;
	/**
	 * 
	 */
	private Integer deptId;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String phone;

}
