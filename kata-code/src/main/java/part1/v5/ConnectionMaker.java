package part1.v5;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

    public Connection makeConnection() throws SQLException;
}
