package com.controller;

import com.bean.Items;
import com.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/list")
    public ModelAndView list(){
        //集合查询
        List<Items> items = itemsService.findAll();
        //存入回显
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("items",items);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping("/save")
    public ModelAndView save(Items items){

        int save = itemsService.save(items);
        if (save>0){
            List<Items> items1 = itemsService.findAll();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("items",items1);
            modelAndView.setViewName("success");
            return modelAndView;
        }else {
            return null;
        }


    }

}
