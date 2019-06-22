import com.es.pojo.Item;
import com.es.service.ItemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @program: crawler
 * @description: ESTest
 * @author: wenlongzhou
 * @create: 2019-06-22 16:24
 **/

public class ESTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //创建索引和映射
    @Test
    public void createIndex() {
        this.elasticsearchTemplate.createIndex(Item.class);
        this.elasticsearchTemplate.putMapping(Item.class);
    }

    //新增
    //修改
    //删除
    //批量保存

}
