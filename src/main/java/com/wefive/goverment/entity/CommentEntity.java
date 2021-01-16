package com.wefive.goverment.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer commentId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer deptId;
	/**
	 * 
	 */
	private Integer busId;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String reply;

    @TableLogic
	private Integer delete1;

}
