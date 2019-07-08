package manage2;


import com.voucher.manage2.service.impl.ReplaceKeywordsServiceImpl;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

/**
 * @author lz
 * @description
 * @date 2019/7/4
 */
public class WordTest {

    @Test
    public void test1() throws IOException {
        ReplaceKeywordsServiceImpl replaceKeywordsService = new ReplaceKeywordsServiceImpl();
        HashMap<String, Object> map = new HashMap<>();
        map.put("Charter", "李四");
        map.put("address", "北京");
        replaceKeywordsService.printWord(map);
    }
}