package com.tm.task;

import com.tm.pojo.Goods;
import com.tm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @program: crawler
 * @description: SpringDataPipeline
 * @author: wenlongzhou
 * @create: 2019-06-21 17:27
 **/

@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private GoodsService goodsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的商品对象
        Goods goods = resultItems.get("goods");

        //判断数据是否不为空
        if (goods != null) {
            //如果不为空把数据保存到数据库
            this.goodsService.save(goods);
        }
    }

}
