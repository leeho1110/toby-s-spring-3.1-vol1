package part4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import part1.v1.User;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserDaoJdbcTest {

    private DataSource dataSource;
    private GenericXmlApplicationContext applicationContext;


    @BeforeEach
    public void setUp(){
        applicationContext = new GenericXmlApplicationContext("applicationContext.xml");
        dataSource = applicationContext.getBean("dataSource", SimpleDriverDataSource.class);
    }

    @Test
    void test_add_when_instanceField_is_null() {
        // given
        UserDao userDao = applicationContext.getBean("userDao4", UserDaoJdbc.class);
        User user = new User();

        // when & then
        assertThatThrownBy(() -> {
            userDao.add(user);
        }).isInstanceOf(DataIntegrityViolationException.class)
                .isInstanceOf(DataAccessException.class)
                .isInstanceOf(RuntimeException.class);
        // org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator -
        // Translating SQLException with SQL state '23502', error code '23502',
        // message [NULL not allowed for column "ID";
        // SQL statement: insert into users (id, name, password) values (?, ?, ?) [23502-212]];
        // SQL was [insert into users (id, name, password) values (?, ?, ?)] for task [PreparedStatementCallback]
    }

    @Test
    void test_add_when_duplicate_user_is_added() {
        // given
        UserDao userDao = applicationContext.getBean("userDao4", UserDaoJdbc.class);
        User user = new User();
        user.setId("id");
        user.setPassword("pw");
        user.setName("name");

        // when & then
        assertThatThrownBy(() -> {
            userDao.add(user);
            userDao.add(user);
        }).isInstanceOf(DuplicateKeyException.class)
            .isInstanceOf(DataAccessException.class)
            .isInstanceOf(RuntimeException.class);
        // org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator -
        // Translating SQLException with SQL state '23505', error code '23505',
        // message [Unique index or primary key violation: "PUBLIC.PRIMARY_KEY_4 ON PUBLIC.USERS(ID) VALUES ( /* 19 */ 'id' )";
        // SQL statement: insert into users (id, name, password) values (?, ?, ?) [23505-212]];
        // SQL was [insert into users (id, name, password) values (?, ?, ?)] for task [PreparedStatementCallback]
    }

    @Test
    public void test_translate_sqlException_to_dataAccessException(){
        // given
        UserDao userDao = applicationContext.getBean("userDao4", UserDaoJdbc.class);
        User user = new User();
        user.setId("id");
        user.setPassword("pw");
        user.setName("name");

        // when & then
        try {
            userDao.add(user);
            userDao.add(user);
        } catch(DuplicateKeyException e) {
            SQLException sqlException = (SQLException) e.getRootCause();
            SQLExceptionTranslator sqlExceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);

            assertThat(sqlExceptionTranslator.translate(null, null, sqlException)).isInstanceOf(DuplicateKeyException.class);
        }
    }
}