package part1.v5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException {
        Connection dConnection = DriverManager.getConnection ("dCompanyDatabaseUrl", "dCompany","");
        return dConnection;
    }
}