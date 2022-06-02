package part3.v3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import part1.v1.User;
import part3.v3.strategy.UserDao;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class UserDaoTest {

    private GenericXmlApplicationContext applicationContext;
    private User user;

    @BeforeEach
    public void setUp(){
        applicationContext = new GenericXmlApplicationContext("applicationContext.xml");

        // 테스트 픽스처에 종속되어선 안되지만, 객체 변경이 없을 것이므로 일시적으로 허용
        user = new User();
        user.setName("이호");
        user.setPassword("25");
    }

    @Transactional
    @Nested
    class test_add {

        @Test
        public void when_use_strategy_pattern() throws SQLException {
            // given
            part3.v3.strategy.UserDao userDao = applicationContext.getBean("userDao3_v3_strategy", UserDao.class);
            user.setId("strategy");

            // when
            userDao.add(user);

            // then
            assertThatNoException();
        }

        @Test
        public void when_use_local_class() throws SQLException {
            // given
            part3.v3.localclass.UserDao userDao = applicationContext.getBean("userDao3_v3_localclass", part3.v3.localclass.UserDao.class);
            user.setId("localClass");

            // when
            userDao.add(user);

            // then
            assertThatNoException();
        }

        @Test
        public void when_use_anonymous_inner_class() throws SQLException {
            // given
            part3.v3.localclass.UserDao userDao = applicationContext.getBean("userDao3_v3_localclass", part3.v3.localclass.UserDao.class);
            user.setId("anonymous");
            // when
            userDao.add(user);

            // then
            assertThatNoException();
        }
    }
}
