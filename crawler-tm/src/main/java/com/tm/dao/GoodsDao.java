package com.tm.dao;

import com.tm.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsDao extends JpaRepository<Goods,Integer> {
}
