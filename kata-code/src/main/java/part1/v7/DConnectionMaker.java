package part1.v7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws SQLException {
        return DriverManager.getConnection ("jdbc:h2:tcp://localhost/~/test", "sa","");
    }
}
