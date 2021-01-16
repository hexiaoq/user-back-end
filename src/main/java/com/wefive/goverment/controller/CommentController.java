package com.wefive.goverment.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wefive.goverment.entity.CommentEntity;
import com.wefive.goverment.service.CommentService;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.common.utils.R;



/**
 * 
 *
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
@RestController
@RequestMapping("goverment/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 用户查看此部门评论列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 用户仅查看自己的评论
     * 实现对话功能
     */
    @RequestMapping("/mylist")
    public R mylist(@RequestParam Map<String, Object> params){
        PageUtils page = commentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commentId}")
    public R info(@PathVariable("commentId") Integer commentId){
		CommentEntity comment = commentService.getById(commentId);

        return R.ok().put("comment", comment);
    }

    /**
     * 用户上传评论
     */
    @RequestMapping("/save")
    public R save(@RequestBody CommentEntity comment){
        comment.setBusId(13);
        comment.setReply("");
		commentService.save(comment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommentEntity comment){
        comment.setBusId(13);
		commentService.updateById(comment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{userId}")
    public R delete(@PathVariable int userId, @RequestParam Integer commentId){
        CommentEntity byId = commentService.getById(commentId);
        if(byId.getUserId()==userId)
        {
//            commentService.removeById(commentId);
            //逻辑删除
            commentService.delete(commentId);

            return R.ok("删除成功");
        }
        return R.error("你没有权限删除此条评论");
    }

}
