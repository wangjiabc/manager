package manage2;

import com.voucher.manage2.service.impl.ReplaceKeywordsServiceImpl;
import com.voucher.manage2.service.impl.Word2PdfServiceImpl;
import org.junit.Test;

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
        System.out.println(new Word2PdfServiceImpl().convert(wordPath));
    }
}
