package manager;

import com.voucher.crab2died.ExcelUtils;
import com.voucher.crab2died.exceptions.Excel4JException;

import modules.Student1;
import modules.Student2;
import modules.StudentScore;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Excel2Module {

	static String pathRoot = System.getProperty("user.home");
	
	public final static String filePath=pathRoot+"\\Desktop\\pasoft";
	
	public final static String path = filePath+"\\零售户信息\\白沙中队.xlsx";

    public void excel2Object() throws Exception {

        System.out.println("读取全部：");
        List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class);
        for (Student1 stu : students) {
            System.out.println(stu);
        }
        System.out.println("读取指定行数：");
        students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 3, 0);
        for (Student1 stu : students) {
            System.out.println(stu);
        }
    }

    public void excel2Object2() {

        
        try {

            // 1)
            // 不基于注解,将Excel内容读至List<List<String>>对象内
            List<List<String>> lists = ExcelUtils.getInstance().readExcel2List(path, 0, 2000, 0);
            System.out.println("读取Excel至String数组：");
            for (List<String> list : lists) {
                System.out.println(list);
            }

            // 2)
            // 基于注解,将Excel内容读至List<Student2>对象内
            // 验证读取转换函数Student2ExpelConverter
            // 注解 `@ExcelField(title = "是否开除", order = 5, readConverter =  Student2ExpelConverter.class)`
            /*List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 0);
            System.out.println("读取Excel至对象数组(支持类型转换)：");
            for (Student1 st : students) {
                System.out.println(st);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 基于注解,将Excel内容读至List<Student2>对象内
    // 验证读取转换函数Student2ExpelConverter，注解 `@ExcelField(title = "是否开除", order = 5, readConverter = Student2ExpelConverter.class)`

    public void testReadConverter() throws Exception {

        List<Student2> students = ExcelUtils.getInstance().readExcel2Objects(path, Student2.class, 0, 0);
        System.out.println("读取Excel至对象数组(支持类型转换)：");
        for (Student2 st : students) {
            System.out.println(st);
        }
    }

    // 测试读取带有公式的单元格，并返回公式的值

    public void testReadExcel_XLS() throws Exception {

        System.out.println(Paths.get(path).toUri().getPath());
        List<StudentScore> projectExcelModels = ExcelUtils.getInstance().readExcel2Objects(path, StudentScore.class);
        System.out.println(projectExcelModels);
    }

    // 测试读取CSV文件

    public void testReadCSV() throws Excel4JException {
        List<Student2> list = ExcelUtils.getInstance().readCSV2Objects("J.csv", Student2.class);
        for (Student2 student2 : list) {
            System.out.println(student2);
        }
    }
}
