package part3.v5.movemethod;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public void deleteAll() throws SQLException {
        jdbcContext.executeSql("delete from users");
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource);
    }
}
