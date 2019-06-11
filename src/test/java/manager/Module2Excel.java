package manager;


import com.alibaba.fastjson.JSONArray;
import com.voucher.crab2died.ExcelUtils;
import com.voucher.crab2died.exceptions.Excel4JException;
import com.voucher.crab2died.sheet.wrapper.MapSheetWrapper;
import com.voucher.crab2died.sheet.wrapper.NoTemplateSheetWrapper;
import com.voucher.crab2died.sheet.wrapper.NormalSheetWrapper;
import com.voucher.crab2died.sheet.wrapper.SimpleSheetWrapper;
import com.voucher.manage.dao.AssetsDAO;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.daoModel.ChartInfo;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.sqlserver.context.Connect;

import modules.Student1;
import modules.Student2;

import org.apache.bcel.generic.NEW;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Module2Excel {

	static String pathRoot = System.getProperty("user.home");
	
	public final static String filePath=pathRoot+"\\Desktop\\pasoft\\零售户信息\\";
	
    ApplicationContext applicationContext = new Connect().get();
    
    CurrentDao currentDao = (CurrentDao) applicationContext.getBean("currentDao");
	
    AssetsDAO assetsDAO = (AssetsDAO) applicationContext.getBean("assetsdao");
    

    public void testObject2Excel() throws Exception {

        //String tempPath = "/normal_template.xlsx";
        List list = new ArrayList<>();
        Room room=new Room();
        room.setLimit(10);
        room.setOffset(0);
        room.setNotIn("id");
        Map map=currentDao.selectTable(room, "guid");
        MyTestUtil.print(map);
        list=(List) map.get("rows");
        
        ChartInfo chartInfo=new ChartInfo();
        chartInfo.setLimit(10);
        chartInfo.setOffset(0);
        chartInfo.setNotIn("id");
        
        Map map2=assetsDAO.getAllChartInfo(10, 0, null, null, new HashedMap<>());
        //list=(List) map2.get("rows");
        MyTestUtil.print(list);
        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");
        // 基于模板导出Excel
        //FileOutputStream os = new FileOutputStream(new File("A.xlsx"));
        //ExcelUtils.getInstance().exportObjects2Excel(tempPath, list, data, Student1.class, false, os);
        //os.close();
        // 不基于模板导出Excel
        ExcelUtils.getInstance().exportObjects2Excel(list, modules.Room.class, true, null, true, filePath+"B.xlsx");

        ExcelUtils.getInstance().exportObjects2CSV(list, modules.Room.class, filePath+"JJ.csv");

    }


    // 基于模板、注解的多sheet导出
    
    public void testObject2BatchSheet() throws Exception {

        List<NormalSheetWrapper> sheets = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Student1> list = new ArrayList<>();
            list.add(new Student1("1010001", "盖伦", "六年级三班"));
            list.add(new Student1("1010002", "古尔丹", "一年级三班"));
            list.add(new Student1("1010003", "蒙多(被开除了)", "六年级一班"));
            list.add(new Student1("1010004", "萝卜特", "三年级二班"));
            list.add(new Student1("1010005", "奥拉基", "三年级二班"));
            list.add(new Student1("1010006", "得嘞", "四年级二班"));
            list.add(new Student1("1010007", "瓜娃子", "五年级一班"));
            list.add(new Student1("1010008", "战三", "二年级一班"));
            list.add(new Student1("1010009", "李四", "一年级一班"));
            Map<String, String> data = new HashMap<>();
            data.put("title", "战争学院花名册");
            data.put("info", "学校统一花名册");
            sheets.add(new NormalSheetWrapper(i, list, data, Student1.class, false));
        }

        String tempPath = "D:\\JProject\\Excel4J\\src\\test\\resources\\normal_batch_sheet_template.xlsx";
        FileOutputStream os = new FileOutputStream(new File("JK.xlsx"));
        // 基于模板导出Excel
        ExcelUtils.getInstance().normalSheet2Excel(sheets, tempPath, "AA.xlsx");
        ExcelUtils.getInstance().normalSheet2Excel(sheets, tempPath, os);
        os.close();

    }

    
    public void testMap2Excel() throws Exception {

        Map<String, List<?>> classes = new HashMap<>();

        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");

        classes.put("class_one", Arrays.asList(
                new Student1("1010009", "李四", "一年级一班"),
                new Student1("1010002", "古尔丹", "一年级三班")
        ));
        classes.put("class_two", Collections.singletonList(
                new Student1("1010008", "战三", "二年级一班")
        ));
        classes.put("class_three", Arrays.asList(
                new Student1("1010004", "萝卜特", "三年级二班"),
                new Student1("1010005", "奥拉基", "三年级二班")
        ));
        classes.put("class_four", Collections.singletonList(
                new Student1("1010006", "得嘞", "四年级二班")
        ));
        classes.put("class_six", Arrays.asList(
                new Student1("1010001", "盖伦", "六年级三班"),
                new Student1("1010003", "蒙多", "六年级一班")
        ));

        ExcelUtils.getInstance().exportMap2Excel("/map_template.xlsx",
                0, classes, data, Student1.class, false, "C.xlsx");
    }

    // Map数据的多sheet导出
   
    public void testMap2BatchSheet() throws Exception {

        List<MapSheetWrapper> sheets = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Map<String, List<?>> classes = new HashMap<>();

            Map<String, String> data = new HashMap<>();
            data.put("title", "战争学院花名册");
            data.put("info", "学校统一花名册");

            classes.put("class_one", Arrays.asList(
                    new Student1("1010009", "李四", "一年级一班"),
                    new Student1("1010002", "古尔丹", "一年级三班")
            ));
            classes.put("class_two", Collections.singletonList(
                    new Student1("1010008", "战三", "二年级一班")
            ));
            classes.put("class_three", Arrays.asList(
                    new Student1("1010004", "萝卜特", "三年级二班"),
                    new Student1("1010005", "奥拉基", "三年级二班")
            ));
            classes.put("class_four", Collections.singletonList(
                    new Student1("1010006", "得嘞", "四年级二班")
            ));
            classes.put("class_six", Arrays.asList(
                    new Student1("1010001", "盖伦", "六年级三班"),
                    new Student1("1010003", "蒙多", "六年级一班")
            ));

            sheets.add(new MapSheetWrapper(i, classes, data, Student1.class, false));
        }
        ExcelUtils.getInstance().mapSheet2Excel(sheets, "/map_batch_sheet_template.xlsx", "CC.xlsx");
    }

  
    public void testList2Excel() throws Exception {
    	/*
        List<List<String>> list2 = new ArrayList<>();
        List<String> header = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> _list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                _list.add(i + " -- " + j);
            }
            list2.add(_list);
            header.add(i + "---栏");
        }
        */
    	
    	  List list = new ArrayList<>();
          Room room=new Room();
          room.setLimit(10);
          room.setOffset(0);
          room.setNotIn("id");
          Map map=currentDao.selectTable(room, "guid");
          MyTestUtil.print(map);
          
          list=(List) map.get("rows");         
    	
          List<Map<String, Object>> fixedTitle=(List<Map<String, Object>>) map.get("fixedTitle");
          
          List<String> headers = new ArrayList<>();
          List<String> fields=new ArrayList<>();
          
          for (int i = 0; i < fixedTitle.size(); i++) {
        	  Map<String, Object> dynTitleMap=fixedTitle.get(i);
              headers.add((String) dynTitleMap.get("title"));
              fields.add((String) dynTitleMap.get("field"));
          }

          JSONArray jsonArray=new JSONArray();
          
          Iterator iterator=list.iterator();
          
          List list2=new ArrayList<>();

          while (iterator.hasNext()) {
			Map map2=(Map) iterator.next();
			Iterator<String> fieldsIterator=fields.iterator();
			List list3=new ArrayList<>();
			while (fieldsIterator.hasNext()) {
				String field=fieldsIterator.next();
				list3.add(map2.get(field));
			}
			list2.add(list3);
			
          }
          
          MyTestUtil.print(list2);
          
          ExcelUtils.getInstance().exportObjects2Excel(list2, headers, filePath+"D.xlsx");
    }

    
    public void uuid() throws IOException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        ExcelUtils.getInstance().exportObjects2Excel(list, new ArrayList<String>() {{
            add("uuid");
        }}, "J.xlsx");
    }

    // 验证日期转换函数 Student2DateConverter
    // 注解 `@ExcelField(title = "入学日期", order = 3, writeConverter = Student2DateConverter.class)`
    
    public void testWriteConverter() throws Exception {

        List<Student2> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Student2(10000L + i, "学生" + i, new Date(), 201, false));
        }
        ExcelUtils.getInstance().exportObjects2Excel(list, Student2.class, true, "sheet0", true, "E.xlsx");
    }

    // 多sheet无模板、基于注解的导出
    
    public void testBatchNoTemplate2Excel() throws Exception {

        List<NoTemplateSheetWrapper> sheets = new ArrayList<>();

        for (int s = 0; s < 3; s++) {
            List<Student2> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                list.add(new Student2(10000L + i, "学生" + i, new Date(), 201, false));
            }
            sheets.add(new NoTemplateSheetWrapper(list, Student2.class, true, "sheet_" + s));
        }
        ExcelUtils.getInstance().noTemplateSheet2Excel(sheets, "EE.xlsx");
    }

    // 多sheet无模板、无注解导出
    
    public void testBatchSimple2Excel() throws Exception {

        // 生成sheet数据
        List<SimpleSheetWrapper> list = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            //表格内容数据
            List<String[]> data = new ArrayList<>();
            for (int j = 0; j < 1000; j++) {

                // 行数据(此处是数组) 也可以是List数据
                String[] rows = new String[5];
                for (int r = 0; r < 5; r++) {
                    rows[r] = "sheet_" + i + "row_" + j + "column_" + r;
                }
                data.add(rows);
            }
            // 表头数据
            List<String> header = new ArrayList<>();
            for (int h = 0; h < 5; h++) {
                header.add("column_" + h);
            }
            list.add(new SimpleSheetWrapper(data, header, "sheet_" + i));
        }
        ExcelUtils.getInstance().simpleSheet2Excel(list, "K.xlsx");
    }

    // 导出csv
    
    public void testExport2CSV() throws Excel4JException {

        List<Student2> list = new ArrayList<>();
        list.add(new Student2(1000001L, "张三", new Date(), 1, true));
        list.add(new Student2(1010002L, "古尔丹", new Date(), 2, false));
        list.add(new Student2(1010003L, "蒙多(被开除了)", new Date(), 6, true));
        list.add(new Student2(1010004L, "萝卜特", new Date(), 3, false));
        list.add(new Student2(1010005L, "奥拉基", new Date(), 4, false));
        list.add(new Student2(1010006L, "得嘞", new Date(), 4, false));
        list.add(new Student2(1010007L, "瓜娃子", new Date(), 5, true));
        list.add(new Student2(1010008L, "战三", new Date(), 4, false));
        list.add(new Student2(1010009L, "李四", new Date(), 2, false));

        ExcelUtils.getInstance().exportObjects2CSV(list, Student2.class, "J.csv");
    }

    // 超大数据量导出csv
    // 9999999数据本地测试小于1min
    
    public void testExport2CSV2() throws Excel4JException {

        List<Student2> list = new ArrayList<>();
        for (int i = 0; i < 9999999; i++) {
            list.add(new Student2(1000001L + i, "路人 -" + i, new Date(), i % 6, true));
        }
        ExcelUtils.getInstance().exportObjects2CSV(list, Student2.class, "L.csv");
    }
}
