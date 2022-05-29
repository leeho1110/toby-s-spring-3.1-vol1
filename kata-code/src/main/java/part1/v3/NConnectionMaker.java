package part1.v3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException {
        Connection nConnection = DriverManager.getConnection ("jdbc:h2:tcp://localhost/~/test", "sa","");
        return nConnection;
    }
}
