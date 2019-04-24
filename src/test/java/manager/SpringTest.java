package manager;

import com.voucher.manage.dao.CurrentDao;
import com.voucher.sqlserver.context.Connect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/*.xml"})
public class SpringTest {
    //@Autowired
    //TestListFieldController testListFieldController;
    @Autowired
    BeanFactory beanFactory;
    ApplicationContext applicationContext = new Connect().get();

    @Test
    public void test1() {
        //testListFieldController.delField(null);
        CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");
        int i = currentDao.alterTable(false, "test", null, "item_a91c9d5ef10061a004fc1d7a08f27a80");
        System.out.println(i);
    }
}
