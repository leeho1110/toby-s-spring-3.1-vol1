package part1.v6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import part1.v1.User;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

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
