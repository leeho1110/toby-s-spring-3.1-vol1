package part3.v5.extractmethod;

import part1.v1.User;
import part3.v5.StatementStrategy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public void deleteAll() throws SQLException {
        executeSql("delete from users");
    }

    private void executeSql(final String query) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                        // 변하는 곳을 인자로 받을 수 있도록 메서드로 추출
                        return conn.prepareStatement(query);
                    }
                }
        );
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource);
    }
}
