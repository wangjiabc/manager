package manage2;

import com.alibaba.fastjson.JSONArray;
import com.voucher.manage.daoModel.Room;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/*.xml"})
public class RoomTest {
    //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-sqlservers.xml");
    //
    //CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");

    @Test
    public void addField() {
        String fieldName = "测试2显示名";
        //currentDao.alterTable(true, "room", fieldName, null);
    }

    @Test
    public void insertTable() throws ClassNotFoundException {
        //insert into [item_room] (guid,build_area,room_property,state)  values (?,?,?,?)]; nested exception is java.sql.SQLException: 列名 'build_area' 无效。
        String guid = "111aaaaaaaaa";
        Room room = new Room();
        room.setGuid(guid);
        room.setAddress("住址1");
        room.setBuild_area(22f);

        JSONArray jsonArray = new JSONArray();

        jsonArray.add("guid");
        jsonArray.add(guid);
        jsonArray.add("item_f63037de77771e05145221676250dc11");
        jsonArray.add("测试1的值");


        //currentDao.insertTable(room, jsonArray.toJSONString());
    }

    @Test
    public void addExcel() throws IOException, BiffException {/*
        List<Object> list = new ArrayList<>();
        String fileName = "C:\\Users\\Administrator\\Desktop\\全市零售户";
        File[] files = new File(fileName).listFiles();
        for (File file : files) {
            getEexcl(file.getAbsolutePath(), list);
        }
        System.out.println(list.size());*/
    }

    private <T> void getEexcl(String fileName, List<T> list) throws IOException, BiffException {
        File file = new File(fileName); // 创建文件对象
        System.out.println("???????????????????????????=" + fileName);
        Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象（WorkBook）
        Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）
        for (int i = 0; i < sheet.getRows(); i++) {
            if (i < 1) {
                continue;
            }
            for (int j = 0; j < sheet.getColumns(); j++) {
                if (j == 6) {
                    Cell cell = sheet.getCell(j, i);
                    if (cell.getContents() == null || "".equals(cell.getContents())) {
                        continue;
                    }
                    System.out.println("电话号码" + cell.getContents());
                }
                if (j == 7) {
                    Cell cell = sheet.getCell(j, i);
                    if (cell.getContents() == null || "".equals(cell.getContents())) {
                        continue;
                    }
                    System.out.println("身份证" + cell.getContents());
                }
            }
        }
    }
}
