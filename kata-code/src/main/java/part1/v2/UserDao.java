package part1.v2;

import part1.v1.User;

import java.sql.*;
public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");
        ps.setString(1,id);

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

    // 중복 제거
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:tcp://localhost/~/test", "sa","");
        return conn;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();

        User user = new User();
        user.setId("leeho");
        user.setName("이호");
        user.setPassword("25");

        userDao.add(user);

        System.out.println(user.getId() + "등록에 성공했습니다!");

        User userLeeho = userDao.get(user.getId());
        System.out.println("userLeeho.name = " + userLeeho.getName());
        System.out.println("userLeeho.password = " + userLeeho.getPassword());
    }
}
