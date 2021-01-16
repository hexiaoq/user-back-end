package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.CommentDao;
import com.wefive.goverment.entity.CommentEntity;
import com.wefive.goverment.service.CommentService;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        if(params.get("user_id")==null)
        {
            IPage<CommentEntity> page = this.page(
                    new Query<CommentEntity>().getPage(params),
                    new QueryWrapper<CommentEntity>().eq("dept_id", params.get("dept_id"))
            );
            return new PageUtils(page);
        }
        else {
            IPage<CommentEntity> page = this.page(
                    new Query<CommentEntity>().getPage(params),
                    new QueryWrapper<CommentEntity>().eq("dept_id", params.get("dept_id"))
                    .eq("user_id",params.get("user_id"))
            );
            return new PageUtils(page);
        }


    }
    @Override
  public void delete(int commentId)

  {

      baseMapper.deleteBatchIds(Arrays.asList(commentId));
  }
}