package db.tools.db;

public class Contant {

    public static final String ORACLE_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    public static final String ORACLE_URL_TEMPLATE = "jdbc:oracle:thin:@%s:%d:%s";

    public static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL_TEMPLATE = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=UTF-8";


    public static final String SQLSQLSERVER2KPLUS_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SQLSQLSERVER2KPLUS_URL_TEMPLATE = "jdbc:sqlserver://%s:%d;DatabaseName=%s";

    public static final String SQLSQLSERVER2K_DRIVER_CLASS = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    public static final String SQLSQLSERVER2K_URL_TEMPLATE = "jdbc:microsoft:sqlserver://%s:%d;DatabaseName=%s";

    public static final String DB2_DRIVER_CLASS = "COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver";
    public static final String DB2_URL_TEMPLATE = "jdbc:db2://%s:%s/%s";

    public static final String DM_DRIVER_CLASS = "dm.jdbc.driver.DmDriver";
    public static final String DM_URL_TEMPLATE = "jdbc:dm://%s:%d/%s";

    public static final String KINGBASE_URL_TEMPLATE = "jdbc:kingbase://%s:%d/%s";
    public static final String KINGBASE_DRIVER_CLASS = "com.kingbase.Driver";

}
