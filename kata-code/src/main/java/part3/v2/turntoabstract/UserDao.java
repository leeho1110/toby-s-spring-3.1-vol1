package part3.v2.turntoabstract;

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
        Connection connection = dataSource.getConnection();

        // prepareStatement를 호출하다가 예외가 발생하는 경우 statement와 connection이 정상적으로 닫히지 않는다.
        PreparedStatement ps = makeStatement(connection);
        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public void deleteAllWithTryCatch() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = makeStatement(conn);
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

    abstract protected PreparedStatement makeStatement(Connection conn) throws SQLException;

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
