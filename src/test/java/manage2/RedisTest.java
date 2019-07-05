package manage2;

import com.voucher.manage2.redis.JedisUtil0;
import com.voucher.manage2.service.impl.ReplaceKeywordsServiceImpl;
import com.voucher.manage2.utils.Word2pdfUtils;
import com.voucher.manage2.utils.WrodToPDF;
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
    public void test1() throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ContractNo", 6666);
        String wordPath = new ReplaceKeywordsServiceImpl().printWord(map);
        System.out.println(wordPath);
//        String wordPath = "";
        WrodToPDF.doc2pdf(wordPath);
    }
}
