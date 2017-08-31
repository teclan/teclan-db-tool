package db.tools.db;

import java.util.Map;

import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.RowListener;

public class DefaultService implements Service {

    private DataSource dataSource;
    private String     name = "default";

    public DefaultService(DataSource dataSource) {

        this.dataSource = dataSource.init();

    }

    public void execWithFile(String sql, Object... paras) {
        new DB(name).exec(sql, paras);

    }

    @Override
    public String getName() {
        return name;
    }

    public boolean attachble() {
        String name = "conn-test";
        try {
            new DB(name).open(dataSource.getDriver(), dataSource.getUrl(),
                    dataSource.getUsername(), dataSource.getPassword());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (new DB(name).hasConnection()) {
                new DB(name).close();
            }
        }
        return true;

    }

    @Override
    public void open() {
        try {
            new DB(name).open(dataSource.getDriver(), dataSource.getUrl(),
                    dataSource.getUsername(), dataSource.getPassword());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() {
        if (new DB(name).hasConnection()) {
            new DB(name).close();
        }
    }

    @Override
    public void select(String sql) {

        new DB(name).find(sql, new RowListener() {

            @Override
            public boolean next(Map<String, Object> row) {
                System.out.println(row.toString());
                return true;
            }
        });
    }

}
