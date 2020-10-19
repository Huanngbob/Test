import com.bean.Items;
import com.service.ItemsService;
import com.service.impl.ItemsServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ServiceTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-service.xml");

        ItemsService itemsService = (ItemsService)applicationContext.getBean("ItemsService");

        List<Items> all = itemsService.findAll();
        System.out.println(all);

    }
}
