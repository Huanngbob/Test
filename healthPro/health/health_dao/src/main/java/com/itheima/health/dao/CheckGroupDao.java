package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.PageResult;
import com.itheima.health.pojo.QueryPageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);


    void addCheckGroupCheckItem(@Param("checkGroupId")Integer checkGroupId, @Param("checkitemId")Integer checkitemId);


    Page<CheckGroup> findByCondition(String queryString);

    CheckGroup findById(int checkGroupId);

    List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId);

    void update(CheckGroup checkGroup);

    void deleteCheckGroupCheckItem(Integer id);

    int findSetmealCountByCheckGroupId(int checkGroupId);

    void deleteById(int checkGroupId);
}
