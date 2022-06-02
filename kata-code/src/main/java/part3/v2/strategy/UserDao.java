package part3.v2.strategy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteAll() throws SQLException {
        StatementStrategy deleteAllStatement = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(deleteAllStatement);
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy deleteAllStatement) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = deleteAllStatement.makePreparedStatement(conn);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if( ps != null){
                try {
                    ps.close();
                } catch (SQLException e){}
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e){}
            }
        }
    }

    public int getCount() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            ps = conn.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e){
            throw e;
        } finally {
            if( rs != null){
                try {
                    rs.close();
                } catch (SQLException e){}
            }
            if( ps != null){
                try {
                    ps.close();
                } catch (SQLException e){}
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e){}
            }
        }
    }
}
