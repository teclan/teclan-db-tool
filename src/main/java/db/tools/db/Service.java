package db.tools.db;

import org.javalite.activejdbc.DB;

public interface Service {

    public default void exec(String sql, Object... paras) {
        if (sql.indexOf("#file") > 0) {
            execWithFile(sql, paras);
        } else
            if (((Object[]) paras) != null && ((Object[]) paras).length > 0) {
            new DB(getName()).exec(sql, paras);
        } else {
            new DB(getName()).exec(sql);
        }
    }

    public void execWithFile(String sql, Object... paras);

    public void select(String sql);

    public String getName();

    public boolean attachble();

    public void open();

    public void close();
}
