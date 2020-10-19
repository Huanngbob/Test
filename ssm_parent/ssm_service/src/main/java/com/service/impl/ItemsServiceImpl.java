package com.service.impl;

import com.bean.Items;
import com.dao.ItemsDao;
import com.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ItemsService")
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsDao itemsDao;
    @Override
    public List<Items> findAll() {
        List<Items> itemsList = itemsDao.findAll();
        return itemsList;
    }

    @Override
    public int save(Items items) {
        int save = itemsDao.save(items);
        return save;
    }
}
