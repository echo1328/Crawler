package com.jsu.service;

import com.jsu.pojo.Item;

import java.util.List;

public interface ItemService {

    //保存商品
    public void save(Item item);

    //根据条件查询商品
    public List<Item> findAll(Item item);

}
