package part3.v4;

import part1.v1.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public void add(final User user) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                        PreparedStatement preparedStatement = conn.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
                        preparedStatement.setString(1, user.getId());
                        preparedStatement.setString(2, user.getName());
                        preparedStatement.setString(3, user.getPassword());
                        return preparedStatement;
                    }
                });
    }

    public void deleteAll() throws SQLException {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                        return conn.prepareStatement("delete from users");
                    }
                }
        );
    }

    // 스프링 DI로 주입받은 DataSource를 JdbcContext에 DI(코드 레벨의 수동)
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public JdbcContext getJdbcContext() {
        return jdbcContext;
    }
}
