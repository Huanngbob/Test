package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.PageResult;
import com.itheima.health.pojo.QueryPageBean;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService  {


    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public List<CheckItem> findAll() {
      List<CheckItem> checkItemList = checkItemDao.findAll();
        return checkItemList;
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {

        //页码与大小
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

                //判断是否有查询条件，如果有条件则要实现模糊查询
        if(!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }

        //条件查询
       Page<CheckItem> page = checkItemDao.findPage(queryPageBean.getQueryString());
        PageResult<CheckItem> pageResult = new PageResult<CheckItem>(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void deleteById(int id) throws MyException{

        int cnt = checkItemDao.findCountByCheckItemId(id);
        //先判断这个检查项是否被检查组使用了
        //调用dao查询检查项的id是否在t_checkgroup_checkitem表中存在记录
        if (cnt>0){
            // 已经被检查组使用了，则不能删除，报错
            //??? health_web能捕获到这个异常吗？
            throw new MyException(MessageConstant.CHECKITEM_IN_USE);
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(int id) {
      CheckItem checkItem =  checkItemDao.findById(id);
        return checkItem;
    }

    @Override
    public void update( CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }
}
