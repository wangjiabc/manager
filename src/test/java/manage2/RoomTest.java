package manage2;

import com.alibaba.fastjson.JSONArray;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/*.xml"})
public class RoomTest {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-sqlservers.xml");

    CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");

    @Test
    public void addField() {
        String fieldName = "测试2显示名";
        currentDao.alterTable(true, "room", fieldName, null);
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


        currentDao.insertTable(room, jsonArray.toJSONString());
    }
}
