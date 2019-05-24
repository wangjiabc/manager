package manager;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage2.utils.MapUtils;
import org.junit.Test;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SomeTest {
    @Test
    public void someTest() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress().toString(); //获取本机ip
        System.out.println(ip);
    }

    @Test
    public void test2() {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("AaAa",
                (String key) -> {
                    System.out.println(key);
                    map.put("BBBB", "value");
                    return "value";
                });
    }

    @Test
    public void test1() {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        List<String> strs1 = Arrays.asList(ary);
        ArrayList<String> strs2 = new ArrayList<>(strs1);
        //strs1.add("d");
        System.out.println(strs1.get(0));
        System.out.println(strs2.get(0));
        ary[0] = "1";
        System.out.println(strs1.get(0));
        System.out.println(strs2.get(0));
        // 预期大于 3，结果是 3
        System.out.println(ary.length);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

    @Test
    public void testDigPow() {
        System.out.println(digPow(92, 1));
    }

    public long digPow(int n, int p) {
        // your code
        long result = 0;
        String str = new Integer(n).toString();
        for (int i = 0, len = str.length(); i < len; i++) {
            int i1 = Integer.valueOf(str.substring(i, i + 1));
            result += Math.pow(i1, p + i);
        }
        return result % n == 0 ? result / n : -1l;
    }
}
