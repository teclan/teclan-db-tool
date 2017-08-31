package db.tools.db;

public class DataSource {

    private String type;
    private String host;
    private int    port;
    private String dbName;
    private String username;
    private String password;

    private String driver;
    private String url;

    public DataSource(String type, String host, int port, String dbName,
            String username, String password) {
        this.type = type;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.username = username;
        this.password = password;

        init();
    }

    public DataSource init() {
        if (type == null) {
            System.out.println("数据库类型未指定 ...");
            return this;
        }
        switch (type) {
        case "oracle":
            driver = Contant.ORACLE_DRIVER_CLASS;
            url = String.format(Contant.ORACLE_URL_TEMPLATE, host, port, dbName);
            break;

        case "mysql":
            driver = Contant.MYSQL_DRIVER_CLASS;
            url = String.format(Contant.MYSQL_URL_TEMPLATE, host, port,
                    dbName);
            break;

        case "sqlserver2k":
            driver = Contant.SQLSQLSERVER2K_DRIVER_CLASS;
            url = String.format(Contant.SQLSQLSERVER2K_URL_TEMPLATE, host, port,
                    dbName);
            break;

        case "sqlserver2k+":
            driver = Contant.SQLSQLSERVER2KPLUS_DRIVER_CLASS;
            url = String.format(Contant.SQLSQLSERVER2KPLUS_URL_TEMPLATE, host,
                    port,
                    dbName);
            break;

        case "DB2":
            driver = Contant.DB2_DRIVER_CLASS;
            url = String.format(Contant.DB2_URL_TEMPLATE, host, port, dbName);
            break;

        case "达梦":
            driver = Contant.DM_DRIVER_CLASS;
            url = String.format(Contant.DM_URL_TEMPLATE, host, port, dbName);
            break;

        case "金仓":
            driver = Contant.KINGBASE_DRIVER_CLASS;
            url = String.format(Contant.KINGBASE_URL_TEMPLATE, host, port,
                    dbName);
            break;

        default:
            System.out.println("不支持数据库类型 " + type);
            break;
        }
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
