package manage2;

import com.voucher.manage2.redis.JedisUtil0;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lz
 * @description
 * @date 2019/5/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml", "classpath:/spring-mybatis.xml"})
public class RedisTest {
    @Test
    public void test1() {
        JedisUtil0.setString("1", "2");
    }
}
