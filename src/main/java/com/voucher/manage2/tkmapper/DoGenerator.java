package com.voucher.manage2.tkmapper;

import cn.hutool.core.util.ClassUtil;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class DoGenerator {
    private static Configuration config;
    private static List<String> warnings;
    private static List<Context> contexts;
    private static String[] tableNames = {"sys_role_router"};
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    //所有已生成实体的类名,全小写
    private static List<String> entityNames = new ArrayList<>();

    static {
        warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        try {
            config = cp.parseConfiguration(getResourceAsStream("generatorConfig-sqlserver.xml"));
            contexts = config.getContexts();
            //清楚xmltable配置
            //初始化所有已生成类的列表;
            Set<Class<?>> classes = ClassUtil.scanPackage("com.voucher.manage2.tkmapper.entity");
            for (Class<?> aClass : classes) {
                entityNames.add(aClass.getSimpleName().toLowerCase());
            }
            addTables(tableNames);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        boolean overwrite = true;
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

    private static void addTables(String[] tableNames) {
        for (Context cont : contexts) {
            cont.getTableConfigurations().clear();
        }
        for (int i = 0; i < tableNames.length; i++) {
            String tableName = tableNames[i];
            addTableConfiguration(tableName);
        }
        Iterator<Context> iterator = contexts.iterator();
        while (iterator.hasNext()) {
            Context context = iterator.next();
            if (context.getTableConfigurations().isEmpty()) {
                iterator.remove();
            }
        }
    }

    public static void addTableConfiguration(String tableName) {
        Context context = getContextByTableName(tableName);
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        tableConfiguration.setCountByExampleStatementEnabled(false);
        tableConfiguration.setUpdateByExampleStatementEnabled(false);
        tableConfiguration.setDeleteByExampleStatementEnabled(false);
        tableConfiguration.setInsertStatementEnabled(false);
        tableConfiguration.setDeleteByPrimaryKeyStatementEnabled(false);
        GeneratedKey generatedKey = new GeneratedKey("id", "JDBC", true, null);
        tableConfiguration.setGeneratedKey(generatedKey);
        tableConfiguration.addIgnoredColumn(new IgnoredColumn("id"));
        context.addTableConfiguration(tableConfiguration);
        //config.addContext(context);
    }

    private static Context getContextByTableName(String tableName) {
        //获取类型后转小写在比较
        if (entityNames.contains(lineToHump(tableName).toLowerCase())) {
            return contexts.get(1);
        } else {
            return contexts.get(0);
        }
    }


    /**
     * 数据库名转类名
     */
    public static String lineToHump(String str) {
        //str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        sb.replace(0, 1, sb.substring(0, 1).toUpperCase());
        return sb.toString();
    }

    @Test
    public void test1() {
        System.out.println(lineToHump("sys_role_menu"));
    }
}
