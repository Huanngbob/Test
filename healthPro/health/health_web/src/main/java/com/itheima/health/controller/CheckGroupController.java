package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.PageResult;
import com.itheima.health.pojo.QueryPageBean;
import com.itheima.health.pojo.Result;
import com.itheima.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        // 调用业务服务
        checkGroupService.add(checkGroup, checkitemIds);
        // 响应结果
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        // 调用业务服务
       PageResult<CheckGroup> pageResult = checkGroupService.findPage(queryPageBean);
        // 响应结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    @GetMapping("/findById")
    public Result findById(int checkGroupId ){
        // 调用业务服务
         CheckGroup checkGroup =checkGroupService.findById(checkGroupId);
        // 响应结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId( int checkGroupId ){
        // 调用业务服务
        List<Integer> checkItemIds =checkGroupService.findCheckItemIdsByCheckGroupId(checkGroupId);
        // 响应结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItemIds);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds ){
        // 调用业务服务
        checkGroupService.update(checkGroup, checkitemIds);
        // 响应结果
        return new Result(true, "编辑更新成功");
    }

    @PostMapping("/deleteById")
    public Result deleteById( int checkGroupId ){
        // 调用业务服务
        checkGroupService.deleteById(checkGroupId);
        // 响应结果
        return new Result(true, "编辑更新成功");
    }


}
