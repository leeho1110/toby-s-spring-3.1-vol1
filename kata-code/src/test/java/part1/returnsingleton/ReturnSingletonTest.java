package part1.returnsingleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import part1.v5.DaoFactory;
import part1.v5.UserDao;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReturnSingletonTest {

    @Test
    public void test_daoFactory_return_diff_obj(){
        // given
        DaoFactory daoFactory = new DaoFactory();
        List<UserDao> daos = new ArrayList<>();

        // when
        daos.add(daoFactory.userDao());
        daos.add(daoFactory.userDao());

        // then
        System.out.println(daos.get(0)); // UserDao@709ba3fb
        System.out.println(daos.get(1)); // UserDao@3d36e4cd
        assertThat(daos.get(0)).isNotEqualTo(daos.get(1));
    }

    @Test
    public void test_applicationContext_return_equal_same_obj(){
        // given
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        // when
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        UserDao userDao2 = applicationContext.getBean("userDao", UserDao.class);

        // then
        System.out.println(userDao); // UserDao@7674b62c
        System.out.println(userDao2); // UserDao@7674b62c
        assertThat(userDao).isEqualTo(userDao2);
    }
}
