package part1.v4;

import part1.v1.User;

import java.sql.SQLException;

public class UserDaoTest {

    // UserDaoTest의 책임은 UserDao와 ConnectionMaker 사이의 런타임 의존성을 설정해주는 것이다.
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new DaoFactory().userDao();

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
