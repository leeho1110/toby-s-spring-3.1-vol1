package part1.v3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao extends UserDao{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection dConnection = DriverManager.getConnection ("dCompanyDatabaseUrl", "dCompany","");
        return dConnection;
    }
}
