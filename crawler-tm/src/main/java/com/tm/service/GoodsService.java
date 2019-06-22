package com.tm.service;

import com.tm.pojo.Goods;

import java.util.List;

public interface GoodsService {

    //保存商品
    public void save(Goods goods);

    public List<Goods> findGoods(Goods goods);

}
