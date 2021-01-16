package com.wefive.goverment.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * 
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
@Data
@Component
@TableName("searchrecord")
public class SearchrecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String busName;
	/**
	 * 
	 */
	private Date searchTime;

}
