package part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import part1.v1.User;
import part1.v1.UserDao;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.deleteAll();
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        // given
        UserDao sut = applicationContext.getBean("userDao", UserDao.class);

        User stub = new User();
        stub.setId("leeho");
        stub.setName("이호");
        stub.setPassword("pwd");

        // when
        sut.add(stub);

        // then
        User result = sut.get(stub.getId());
        assertThat(result.getName()).isEqualTo(result.getName());
        assertThat(result.getPassword()).isEqualTo(result.getPassword());
    }

}
