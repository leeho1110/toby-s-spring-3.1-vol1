package part3.v4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private ApplicationContext applicationContext;
    private UserDao userDao;

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
