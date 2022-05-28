package part1.v5;

import part1.v1.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new DConnectionMaker(); // 구현 클래스 노출됨!!
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

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
