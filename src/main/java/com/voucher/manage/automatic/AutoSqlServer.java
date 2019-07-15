package com.voucher.manage.automatic;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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

public class AutoSqlServer {
    private final static String url = DBUtils.url;
    private final static String dataBase = DBUtils.dataBase;
    private String[] colNames;
    private String[] colType;
    private int[] colSize;
    private boolean f_util_date = false;
    private boolean f_Clob = false;
    private boolean f_Blob = false;


    public AutoSqlServer() {
    }

    public AutoSqlServer(String url, String dataBase, String tabName, String filePath) {
        Connection conn = null;
        String sql = "select top 1 * from " + tabName;
        System.out.println("sql=" + sql);

        try {
            conn = DBUtils.getConnection(url);
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSetMetaData rsmd = prep.getMetaData();
            System.out.println("rsmd=" + rsmd);
            int size = rsmd.getColumnCount();
            this.colNames = new String[size];
            this.colType = new String[size];
            this.colSize = new int[size];

            for (int i = 0; i < rsmd.getColumnCount(); ++i) {
                this.colNames[i] = rsmd.getColumnName(i + 1);
                this.colType[i] = rsmd.getColumnTypeName(i + 1);
                if (this.colType[i].equalsIgnoreCase("datetime")) {
                    this.f_util_date = true;
                }

                if (this.colType[i].equalsIgnoreCase("text")) {
                    this.f_Clob = true;
                }

                if (this.colType[i].equalsIgnoreCase("image")) {
                    this.f_Blob = true;
                }

                this.colSize[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = this.parse(dataBase, tabName, this.colNames, this.colType, this.colSize);
            FileWriter fw = new FileWriter(filePath + this.initCap(tabName) + ".java");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

    }

    private String parse(String dataBase, String tabName, String[] colNames, String[] colType, int[] colSize) {
        StringBuffer sb = new StringBuffer();
        if (this.packageName != null && !this.packageName.equals("")) {
            sb.append(this.packageName);
        }

        if (this.f_util_date) {
            sb.append("import java.util.Date;\r\n");
            sb.append("\n");
        }

        if (this.f_Clob) {
            sb.append("import java.sql.Clob;\r\n");
            sb.append("\n");
        }

        if (this.f_Blob) {
            sb.append("import java.sql.Blob;\r\n");
            sb.append("\n");
        }

        sb.append("import java.io.Serializable;\n\n");
        sb.append("import com.voucher.manage.daoSQL.annotations.*;\n\n");
        String prefix = "[";
        sb.append("@DBTable(name=\"" + prefix + tabName + "]\")\n");
        sb.append("public class " + this.initCap(tabName) + " implements Serializable{\r\n");
        sb.append("\n");
        sb.append("    private static final long serialVersionUID = 1L;\n\n");
        this.processAllAttrs(sb);
        this.processAllMethod(sb);
        this.processBasicTerm(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < this.colNames.length; ++i) {
            sb.append("\tpublic void set" + this.initCap(this.colNames[i]) + "(" + this.sqlType2JavaType(this.colType[i]) + " " + this.colNames[i] + "){\r\n");
            sb.append("\t\tthis." + this.colNames[i] + " = " + this.colNames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\n");
            sb.append("\tpublic " + this.sqlType2JavaType(this.colType[i]) + " get" + this.initCap(this.colNames[i]) + "(){\r\n");
            sb.append("\t\treturn " + this.colNames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\n");
        }

    }

    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < this.colNames.length; ++i) {
            String varType = this.sqlType2JavaType(this.colType[i]);
            if (varType != null) {
                if (varType.equals("String")) {
                    sb.append("    @SQLString(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Float")) {
                    sb.append("    @SQLFloat(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Double")) {
                    sb.append("    @SQLDouble(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Boolean")) {
                    sb.append("    @SQLBoolean(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Byte")) {
                    sb.append("    @SQLByte(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Date")) {
                    sb.append("    @SQLDateTime(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Integer")) {
                    sb.append("    @SQLInteger(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Long")) {
                    sb.append("    @SQLLong(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Short")) {
                    sb.append("    @SQLShort(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Clob")) {
                    sb.append("    @SQLClob(name=\"" + this.colNames[i] + "\")\n");
                } else if (varType.equals("Blob")) {
                    sb.append("    @SQLBlob(name=\"" + this.colNames[i] + "\")\n");
                }
            }

            sb.append("\tprivate " + varType + " " + this.colNames[i] + ";\r\n");
            sb.append("\n");
        }

    }

    private String initCap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (!sqlType.equalsIgnoreCase("decimal") && !sqlType.equalsIgnoreCase("numeric") && !sqlType.equalsIgnoreCase("double") && !sqlType.equalsIgnoreCase("real")) {
            if (!sqlType.equalsIgnoreCase("money") && !sqlType.equalsIgnoreCase("smallmoney")) {
                if (!sqlType.equalsIgnoreCase("varchar") && !sqlType.equalsIgnoreCase("char") && !sqlType.equalsIgnoreCase("nvarchar") && !sqlType.equalsIgnoreCase("nchar")) {
                    if (!sqlType.equalsIgnoreCase("datetime") && !sqlType.equalsIgnoreCase("date")) {
                        if (sqlType.equalsIgnoreCase("image")) {
                            return "Blob";
                        } else if (sqlType.equalsIgnoreCase("text")) {
                            return "Clob";
                        } else {
                            return sqlType.equalsIgnoreCase("int identity") ? "Integer" : null;
                        }
                    } else {
                        return "Date";
                    }
                } else {
                    return "String";
                }
            } else {
                return "Double";
            }
        } else {
            return "Double";
        }
    }

    public static List<String> getTabNames(String url, String dataBase) {
        Connection conn = null;
        String sql = "SELECT Name FROM " + dataBase + "..SysObjects Where XType='U' ORDER BY Name";
        ArrayList tabNames = null;

        try {
            conn = DBUtils.getConnection(url);
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            tabNames = new ArrayList();

            for (int var7 = 0; rs.next(); ++var7) {
                tabNames.add(rs.getString("NAME"));
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return tabNames;
    }

    private void processBasicTerm(StringBuffer sb) {
        sb.append("\n\n\n/*\n*数据库查询参数\n*/\n");
        sb.append("    @QualifiLimit(name=\"limit\")\n");
        sb.append("    private Integer limit;\n");
        sb.append("    @QualifiOffset(name=\"offset\")\n");
        sb.append("    private Integer offset;\n");
        sb.append("    @QualifiNotIn(name=\"notIn\")\n");
        sb.append("    private String notIn;\n");
        sb.append("    @QualifiSort(name=\"sort\")\n");
        sb.append("    private String sort;\n");
        sb.append("    @QualifiOrder(name=\"order\")\n");
        sb.append("    private String order;\n");
        sb.append("    @QualifiWhere(name=\"where\")\n");
        sb.append("    private String[] where;\n");
        sb.append("    @QualifiWhereTerm(name=\"whereTerm\")\n");
        sb.append("    private String whereTerm;\n");
        sb.append("\n\n");
        String[] term = new String[]{"limit", "offset", "notIn", "sort", "order", "where", "whereTerm"};
        String[] termType = new String[]{"Integer", "Integer", "String", "String", "String", "String[]", "String"};

        for (int i = 0; i < term.length; ++i) {
            sb.append("\tpublic void set" + this.initCap(term[i]) + "(" + termType[i] + " " + term[i] + "){\r\n");
            sb.append("\t\tthis." + term[i] + " = " + term[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\n");
            sb.append("\tpublic " + termType[i] + " get" + this.initCap(term[i]) + "(){\r\n");
            sb.append("\t\treturn " + term[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\n");
        }

    }

    static String filePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "\\pasoft\\";

    static {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private String packageName = "package com.voucher.manage.daoModel;\n\n";
    static String tabNameOne = "room";

    public static void main(String[] args) {
        doOneTab(tabNameOne);
        //doAllTab();
        System.out.println(filePath);
    }

    private static void doOneTab(String tabNameOne) {
        new AutoSqlServer(url, dataBase, tabNameOne, filePath);
    }

    private static void doAllTab() {
        List<String> tabNames = getTabNames(url, dataBase);
        Iterator<String> iterator = tabNames.iterator();

        for (int i = 0; iterator.hasNext(); ++i) {
            String tabName = iterator.next();
            new AutoSqlServer(url, dataBase, tabName, filePath);
            System.out.println(i + "   " + tabName);
        }
    }
}
