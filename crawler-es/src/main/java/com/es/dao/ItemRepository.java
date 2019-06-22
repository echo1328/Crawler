package com.es.dao;

import com.es.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item,Integer> {
}
