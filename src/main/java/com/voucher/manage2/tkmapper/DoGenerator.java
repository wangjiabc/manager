package com.voucher.manage2.tkmapper;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class DoGenerator {
    private static Configuration config;
    private static List<String> warnings;
    private static Context context;
    private static String[] tableNames = {"room_in", "room_out","room_log"};

    static {
        warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        try {
            config = cp.parseConfiguration(getResourceAsStream("generatorConfig-sqlserver.xml"));
            context = config.getContexts().get(0);
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
        context.getTableConfigurations().clear();
        for (int i = 0; i < tableNames.length; i++) {
            String tableName = tableNames[i];
            addTableConfiguration(tableName);
        }
    }

    public static void addTableConfiguration(String tableName) {
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

}
