package part3.v4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private GenericXmlApplicationContext applicationContext;
    private UserDao userDao;

    @BeforeEach
    public void setUp(){
        applicationContext = new GenericXmlApplicationContext("applicationContext.xml");
    }

    @DisplayName("스프링 DI된 DataSource와 UserDao 수동 DI로 주입된 DataSource가 동일한가")
    @Test
    public void test_dataSource_indirect_di() {
        // given
        UserDao userDao = applicationContext.getBean("userDao3_v4", UserDao.class);

        // when
        JdbcContext jdbcContext = userDao.getJdbcContext();
        DataSource dataSourceInjectedBySpring = userDao.getDataSource();
        DataSource dataSourceIndirectInjectedByUserDao = jdbcContext.getDataSource();

        // then
        assertThat(dataSourceIndirectInjectedByUserDao).isEqualTo(dataSourceInjectedBySpring);
    }
}
