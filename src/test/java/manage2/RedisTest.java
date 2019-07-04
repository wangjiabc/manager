package manage2;

import com.voucher.manage2.redis.JedisUtil0;
import com.voucher.manage2.service.impl.ReplaceKeywordsServiceImpl;
import com.voucher.manage2.utils.Word2pdfUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author lz
 * @description
 * @date 2019/5/30
 */

public class RedisTest {

    @Test
    public  void Test1() throws IOException {
        String wordPath = new ReplaceKeywordsServiceImpl().printWord(new HashMap<>());
        System.out.println(wordPath);
//        String wordPath = "";
        Word2pdfUtils.wToPdfChange(wordPath);
    }
}
