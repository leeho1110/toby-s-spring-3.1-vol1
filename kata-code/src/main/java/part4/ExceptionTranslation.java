package part4;

import org.springframework.dao.DuplicateKeyException;
import part1.v1.User;
import part1.v1.UserDao;

import java.sql.*;

public class ExceptionTranslation {

    public void throwConvertedToProperException() {
        UserDao userDao = new UserDao();
        User stub = new User();
        stub.setName("leeho");
        stub.setId("25");
        stub.setPassword("pw");

        try {
            // success
            userDao.add(stub);
            // throw org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException!!
            userDao.add(stub);
        } catch (SQLException e) {
            // org/h2/api/ErrorCode.public static final int DUPLICATE_KEY_1 = 23505;
            if (e.getErrorCode() == 23505) {
                // convert to proper Exception
                throw new DuplicateUserIdException(e.getCause());
            } else {
                throw new DuplicateKeyException(e.getMessage());
            }
        }
    }
    class UserDao {

        public void add(User user) throws SQLException {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values (?,?,?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            ps.close();
            conn.close();
        }
        public User get(String id) throws ClassNotFoundException, SQLException {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");
            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            User user = new User();
            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));

            resultSet.close();
            ps.close();
            conn.close();

            return user;
        }
    }
}
