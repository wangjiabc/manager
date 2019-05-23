package manager;

import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage2.tkmapper.mapper.TableAliasMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml","classpath:/spring-mybatis.xml"})
public class SpringTest {
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private CurrentDao currentDao;
    @Autowired
    private TableAliasMapper tableAliasMapper;

    @Test
    public void test1() {

        List<Map<String, Object>> dynLineInfo = tableAliasMapper.getDynLineInfo();
        System.out.println(dynLineInfo);
    }
}
