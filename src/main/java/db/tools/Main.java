package db.tools;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import db.tools.db.DataSource;
import db.tools.db.DefaultService;
import db.tools.db.Service;

public class Main {

    private static String TYPE;
    private static String HOST;
    private static int    PORT;
    private static String DBNAME;
    private static String USERNAME;
    private static String PASSWORD;

    private static Console console = System.console();
    private static Service service;

    public static void main(String[] args) throws IOException {

        if (args.length > 0 && "-h".equals(args[0])) {
            help();
        } else {
            service = getService();
            exec();
        }
    }

    private static void help() {
        System.out.println(
                "\tINSERT 操作时，如果想插入文件至 `blob` 字段，请使用 `#file：file-path` \n"
                        + "\t例如：insert into maoguang (id,name,a_blob) \n "
                        + "\tvalues (100,'rrr',#file:/home/dev/fabfile.py) \n "
                        + "\t即可将文件 /home/dev/fabfile.py 插入到 maoguang 表\n"
                        + "\t的 `a_blob` 字段。\n"
                        + "\tUPDATE 和 update 不支持 #file:file-path 形式\n"
                        + "\n使用本程序操作数据库时，直接在控制台至此嗯 java -jar 加此 jar包文件即可。");

    }

    private static void exec() {

        String sql = null;
        while (true) {

            try {
                System.out.print("输入SQL：");
                sql = console.readLine();

                if (sql.lastIndexOf(";") == sql.length() - 1) {
                    sql = sql.substring(0, sql.length() - 1);
                }

                String tmp = sql.replaceAll("(?i)delete", "delete")
                        .replaceAll("(?i)update", "update")
                        .replaceAll("(?i)select", "select").trim();

                if (tmp.startsWith("delete") || tmp.startsWith("update")) {
                    deleteOrUpdate(sql);
                    System.out.println("\n\n删除或更新成功 ... \n");
                } else if (tmp.startsWith("select")) {
                    select(sql);
                    System.out.println("\n\n查询成功 ... \n");
                } else {
                    insert(sql);
                    System.out.println("\n\n插入成功 ... \n");
                }

            } catch (Exception e) {
                System.out.println(String.format("%s \n\n", "执行失败 ... " + e));
            } finally {
                service.close();
            }
        }
    }

    private static void select(String sql) {
        service.open();
        service.select(sql);
        service.close();
    }

    private static void deleteOrUpdate(String sql) {
        service.open();
        service.exec(sql, null);
        service.close();
    }

    private static void insert(String sql) throws IOException {
        if (sql.lastIndexOf(";") == sql.length() - 1) {
            sql = sql.substring(0, sql.length() - 1);
        }
        Object[] v = getInsertvalues(sql);
        String execSql = getInsertSql(sql, v.length);

        service.open();
        service.exec(execSql, v);
        service.close();
    }

    private static Service getService() {
        do {
            do {
                showDbTypeMenu();
                String type = console.readLine();

                if (type != null && Integer.valueOf(type) > 0
                        && Integer.valueOf(type) < 8) {
                    TYPE = type;
                    break;
                } else {
                    System.out.println("输入无效，数据库类型选择失败！\n");
                }
            } while (true);

            System.out.print("\n主机>>");
            HOST = console.readLine();

            System.out.print("\n端口>>");
            PORT = Integer.valueOf(console.readLine());

            System.out.print("\n数据库>>");
            DBNAME = console.readLine();

            System.out.print("\n用户>>");
            USERNAME = console.readLine();

            System.out.print("\n密码>>");
            PASSWORD = console.readLine();

            DataSource ds = new DataSource(getRealType(TYPE), HOST, PORT,
                    DBNAME, USERNAME, PASSWORD);

            service = new DefaultService(ds);

            if (service.attachble()) {
                System.out.println("数据库连接成功 ... \n");
                break;
            } else {
                System.out.println("数据源配置错误或数据库无法连接 ... \n");
            }

        } while (true);

        return service;
    }

    private static void showDbTypeMenu() {
        System.out.println("===== 选择数据库 ======");
        System.out.println("1. oracle");
        System.out.println("2. mysql");
        System.out.println("3. sqlserver2k");
        System.out.println("4. sqlserver2k+");
        System.out.println("5. BD2");
        System.out.println("6. 达梦");
        System.out.println("7. 金仓");
        System.out.println("=====================");
        System.out.print("请选择 >> ");
    }

    private static String getRealType(String type) {

        switch (type) {
        case "1":
            return "oracle";

        case "2":
            return "mysql";

        case "3":
            return "sqlserver2k";

        case "4":
            return "sqlserver2k+";
        case "5":
            return "DB2";
        case "6":
            return "dm";
        case "7":
            return "kingbase";
        }

        return "";
    }

    private static Object[] getInsertvalues(String sql) throws IOException {
        sql = sql.replaceAll("(?i)values", "values");
        sql = sql.substring(sql.indexOf("values") + 6, sql.length()).trim();
        sql = sql.substring(1, sql.length() - 1);

        String[] o = sql.split(",");
        Object[] v = new Object[o.length];

        for (int i = 0; i < v.length; i++) {
            if (o[i].indexOf("#file") >= 0) {
                v[i] = Files.readAllBytes(Paths.get(
                        o[i].substring(o[i].indexOf(":") + 1, o[i].length())));
            } else {
                v[i] = o[i];
            }
        }
        return v;
    }

    private static String getInsertSql(String sql, int paramaterLength) {
        sql = sql.replaceAll("(?i)values", "values");
        sql = sql.substring(0, sql.lastIndexOf("values"));

        String[] paras = new String[paramaterLength];
        for (int i = 0; i < paras.length; i++) {
            paras[i] = "?";
        }
        return sql + "values (" + String.join(",", paras) + ")";
    }
}
