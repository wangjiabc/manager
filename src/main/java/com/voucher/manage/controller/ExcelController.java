package com.voucher.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.voucher.crab2died.ExcelUtils;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.dao.ExcelDAO;
import com.voucher.manage.dao.HireDAO;
import com.voucher.manage.daoModel.Room;
import com.voucher.manage.singleton.Singleton;
import com.voucher.manage.tools.CopyFile;
import com.voucher.manage.tools.MyTestUtil;

import com.voucher.manage.singleton.*;

class DBUtils {
    private static Properties prop = new Properties();
    //static String url = "jdbc:jtds:sqlserver://223.86.150.188:1433/";
    //static String dataBase = "manage";
    static String url;
    static String dataBase;

    static {
        ClassLoader loader = DBUtils.class.getClassLoader();
        InputStream in = loader.getResourceAsStream("jdbc.properties");
        try {
            prop.load(in);
            Class.forName(prop.getProperty("sql_driverClassName"));
            url = prop.getProperty("sql_url");
            dataBase = url.substring(url.lastIndexOf("/") + 1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String url) throws Exception {
        return DriverManager.getConnection(url, prop.getProperty("sql_username"), prop.getProperty("sql_password"));
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private CurrentDao currentDao;
    
	static String pathRoot = System.getProperty("user.home");
	
	public final static String filePath=pathRoot+"\\Desktop\\pasoft\\excel\\";

	@Autowired
    private ExcelDAO excelDAO;
	
    @RequestMapping("/listToExcel")
    public @ResponseBody Map listToExcel(@RequestParam Integer limit,
    		@RequestParam Integer offset,HttpServletRequest request) throws Exception {
    	
    	  List list = new ArrayList<>();
    	  Room room=new Room();
    	  room.setLimit(limit);
    	  room.setOffset(offset);
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
          
          Class className = room.getClass();
          String tableName = className.getName().replace("com.voucher.manage.daoModel.", "");   
          
          File targetFile=new File(filePath);
          
          if(!targetFile.exists()){
              targetFile.mkdirs();//创建目录
              System.out.println("目录不存在");
          }
          
          ExcelUtils.getInstance().exportObjects2Excel(list2, headers, filePath+tableName+".xlsx");
          
          String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
          
          CopyFile.setFile(imgPath, filePath+tableName+".xlsx", tableName+".xlsx");
          
          Map map2=new HashMap<>();
          
          map2.put("url=", Singleton.filePath+"\\"+tableName+".xlsx");
          
          return map2;
          
    }
	
    @RequestMapping("/getTableNames")
    public @ResponseBody List<String> getTableNames() {
        Connection conn = null;
        String sql = "SELECT Name FROM " + DBUtils.dataBase + "..SysObjects Where XType='U' ORDER BY Name";
        ArrayList tabNames = null;

        try {
            conn = DBUtils.getConnection(DBUtils.url);
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            tabNames = new ArrayList();

			String REGEX = "item_";
			Pattern pattern = Pattern.compile(REGEX);
			
			String REGEX2 = "sys_";
			Pattern pattern2 = Pattern.compile(REGEX2);
			
            for (int var7 = 0; rs.next(); ++var7) {
            	
            	Matcher matcher = pattern.matcher(rs.getString("NAME"));
  				if(matcher.find()){
  					continue;
  				}
  				Matcher matcher2 = pattern2.matcher(rs.getString("NAME"));
  				if(matcher2.find()){
  					continue;
  				}
  				
                tabNames.add(rs.getString("NAME"));
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return tabNames;
    }
   
    
    @RequestMapping("/getTableColumns")
    public @ResponseBody List<String> getTableColumns(@RequestParam String tableName) {
        Connection conn = null;
        String sql = "select top 1 * from " + tableName;
        ArrayList tabNames = null;

        List colNames = new ArrayList<>();
        String[] colType;
        int[] colSize;
        
        try {
            conn = DBUtils.getConnection(DBUtils.url);
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSetMetaData rsmd = prep.getMetaData();
            System.out.println("rsmd=" + rsmd);
            int size = rsmd.getColumnCount();           
            colType = new String[size];
            colSize = new int[size];
            boolean f_util_date = false;
            boolean f_Clob = false;
            boolean f_Blob = false;
            
            for (int i = 0; i < rsmd.getColumnCount(); ++i) {
                colNames.add(rsmd.getColumnName(i + 1));
                colType[i] = rsmd.getColumnTypeName(i + 1);
                if (colType[i].equalsIgnoreCase("datetime")) {
                    f_util_date = true;
                }

                if (colType[i].equalsIgnoreCase("text")) {
                    f_Clob = true;
                }

                if (colType[i].equalsIgnoreCase("image")) {
                    f_Blob = true;
                }

                colSize[i] = rsmd.getColumnDisplaySize(i + 1);
            }

        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return colNames;
    }
    
    @RequestMapping("/getExcelLabel")
    public @ResponseBody List getExcelLabel(@RequestParam("file") MultipartFile file,
    		HttpServletRequest request) {

    	String cookie=request.getSession().getId();
    	   	
    	List<String> list=new ArrayList<>();

    	Map<String,Object> map=new HashMap<>();
    	
    	File targetFile=new File(filePath+file.getName());
        
        if(!targetFile.exists()){
            targetFile.mkdirs();//创建目录
            System.out.println("目录不存在");
        }
        
        try {
        	file.transferTo(targetFile);
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		} 

        
    	map.put("excelPath", filePath+file.getName());
		map.put("startTime", new Date());
		
		LinkedHashMap<String, Map<String,Object>> linkedHashMap=Singleton.getInstance().getRegisterMapLong();
		
    	linkedHashMap.put(cookie, map);
    	
        try {
        	 // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = new FileInputStream(new File(filePath+file.getName()));
            Workbook wb = WorkbookFactory.create(inputStream);
            // 不基于注解,将Excel内容读至List<List<String>>对象内
            List<List<String>> lists = ExcelUtils.getInstance().readExcel2(wb, 0, 0, 0);
            System.out.println("读取Excel至String数组：");
            for (List<String> l : lists) {
                list=l;
            }

            // 2)
            // 基于注解,将Excel内容读至List<Student2>对象内
            // 验证读取转换函数Student2ExpelConverter
            // 注解 `@ExcelField(title = "是否开除", order = 5, readConverter =  Student2ExpelConverter.class)`
            /*List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 0);
            SystemConstant.out.println("读取Excel至对象数组(支持类型转换)：");
            for (Student1 st : students) {
                SystemConstant.out.println(st);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @RequestMapping("/intoList")
    public @ResponseBody Map intoList(@RequestParam String json,Integer limitLine,
            Integer offsetLine,HttpServletRequest request) {
    	    	
    	Map<String, String> jsonMap = 
    			JSONObject.parseObject(json, new TypeReference<Map<String, String>>(){});
    	
    	String cookie=request.getSession().getId();
	   	
    	LinkedHashMap<String, Map<String,Object>> linkedHashMap=Singleton.getInstance().getRegisterMapLong();
    	
    	Map<String,Object> linkedMap=linkedHashMap.get(cookie);
    	
    	String excelPath=(String) linkedMap.get("excelPath");
    	
    	Map map=new HashMap<>();
    	
    	map.put("result", "输入值为空");
    	
    	if(limitLine==null)
    		limitLine=0;
    	
        try {
        	 // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = new FileInputStream(new File(excelPath));
            Workbook wb = WorkbookFactory.create(inputStream);
            // 不基于注解,将Excel内容读至List<List<String>>对象内
            Sheet sheet = wb.getSheetAt(0);
            if(offsetLine==null)
            	offsetLine=sheet.getLastRowNum();
            System.out.println("limitLine="+limitLine+"   offsetLine="+offsetLine);
            List<List<String>> lists = ExcelUtils.getInstance().readExcel2(wb, limitLine, offsetLine, 0);

            // 2)
            // 基于注解,将Excel内容读至List<Student2>对象内
            // 验证读取转换函数Student2ExpelConverter
            // 注解 `@ExcelField(title = "是否开除", order = 5, readConverter =  Student2ExpelConverter.class)`
            /*List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 0);
            SystemConstant.out.println("读取Excel至对象数组(支持类型转换)：");
            for (Student1 st : students) {
                SystemConstant.out.println(st);
            }*/
            
            String s=excelDAO.insertTable(jsonMap, lists);
            
            map.put("result", s);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "导入失败");
        }
        
        return map;
    }
    
}
