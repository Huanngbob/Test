package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.PageResult;
import com.itheima.health.pojo.QueryPageBean;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = CheckGroupService.class )
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    CheckGroupDao checkGroupDao;

    @Override
    public CheckGroup findById(int checkGroupId) {
        CheckGroup checkGroup = checkGroupDao.findById(checkGroupId);
        return checkGroup;
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId) {
        List<Integer> CheckItemIds =  checkGroupDao.findCheckItemIdsByCheckGroupId(checkGroupId);
        return CheckItemIds;
    }

    @Override
    @Transactional
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        //先更新checkgroup
        checkGroupDao.update(checkGroup);
        //再根据checkgroup的id删除关系表
        Integer checkGroupId = checkGroup.getId();
        checkGroupDao.deleteCheckGroupCheckItem(checkGroupId);
        //重新添加关系表
        if (checkitemIds!=null){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);

            }
        }


    }

    @Override
    @Transactional
    public void deleteById(int checkGroupId) {
       int count = checkGroupDao.findSetmealCountByCheckGroupId(checkGroupId);
       if (count>0){
           throw new MyException(MessageConstant.CHECKGROUP_IN_USE);
       }
        // 没有被套餐使用,就可以删除数据
        // 先删除检查组与检查项的关系
        checkGroupDao.deleteCheckGroupCheckItem(checkGroupId);
        // 删除检查组
        checkGroupDao.deleteById(checkGroupId);
    }


    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {

        //先添加检查组信息
        checkGroupDao.add(checkGroup);

        Integer checkGroupId = checkGroup.getId();

        //再添加检查组关系表信息
        //一个一个关系id添加
        if (checkitemIds!=null){// 有钩选
            for (Integer checkitemId : checkitemIds) {

                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }


    }

    @Override
    @Transactional
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //使用分页助手分页查询
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //判断是否有条件，有条件则需要进行模糊查询

        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){

            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }


       Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
       return new PageResult(page.getTotal(),page.getResult());
    }
}
