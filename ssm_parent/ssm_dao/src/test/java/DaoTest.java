import com.bean.Items;
import com.dao.ItemsDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class DaoTest {
    @Test
    public void test(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        ItemsDao itemsDao = (ItemsDao)applicationContext.getBean("itemsDao");
        List<Items> itemsList = itemsDao.findAll();
        System.out.println(itemsList);
//
//        Items items = new Items();
//
//        items.setName("商品名称");
//        items.setPrice(16666f);
//        items.setCreatetime(new Date());
//        itemsDao.save(items);
    }
}
