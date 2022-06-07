package part3.v3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import part1.v1.User;
import part3.v3.strategy.UserDao;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatNoException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@Transactional
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;
    private User user;

    @BeforeEach
    public void setUp(){
        // 테스트 픽스처에 종속되어선 안되지만, 객체 변경이 없을 것이므로 일시적으로 허용
        user = new User();
        user.setName("이호");
        user.setPassword("25");
    }

    @Nested
    class test_add {

        @Test
        public void when_use_strategy_pattern() throws SQLException {
            // given
            part3.v3.strategy.UserDao userDao = context.getBean("userDao3_v3_strategy", UserDao.class);
            user.setId("strategy");

            // when
            userDao.add(user);

            // then
            assertThatNoException();
        }

        @Test
        public void when_use_local_class() throws SQLException {
            // given
            part3.v3.localclass.UserDao userDao = context.getBean("userDao3_v3_localclass", part3.v3.localclass.UserDao.class);
            user.setId("localClass");

            // when
            userDao.add(user);

            // then
            assertThatNoException();
        }

        @Test
        public void when_use_anonymous_inner_class() throws SQLException {
            // given
            part3.v3.localclass.UserDao userDao = context.getBean("userDao3_v3_localclass", part3.v3.localclass.UserDao.class);
            user.setId("anonymous");

            // when
            userDao.add(user);

            // then
            assertThatNoException();
        }
    }
}
