package com.tm.service.impl;

import com.tm.dao.GoodsDao;
import com.tm.pojo.Goods;
import com.tm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: crawler
 * @description: GoodsServiceImpl
 * @author: wenlongzhou
 * @create: 2019-06-22 20:00
 **/

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    @Transactional
    public void save(Goods goods) {

        //根据商品名称和url查询数据
        Goods params = new Goods();
        params.setName(goods.getName());
        params.setUrl(goods.getUrl());

        //执行查询
        List<Goods> list = this.findGoods(params);

        if (list.size() == 0) {
            this.goodsDao.saveAndFlush(goods);
        }

    }

    @Override
    public List<Goods> findGoods(Goods goods) {

        //设置查询条件
        Example example = Example.of(goods);

        //执行查询
        List<Goods> list = this.goodsDao.findAll(example);

        return list;

    }

}
