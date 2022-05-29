package part1.v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection nConnection = DriverManager.getConnection ("jdbc:h2:tcp://localhost/~/test", "sa","");
        return nConnection;
    }
}
