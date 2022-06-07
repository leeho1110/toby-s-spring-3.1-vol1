package part3.part1.xmlconfig;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import part1.v7.UserDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class XmlConfigurationText {

    @Test
    public void test_xml_configuration_userDao(){
        // given
        GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");

        // when
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

        // then
        assertThatNoException();

    }

    @Test
    public void test_xml_configuration_dataSoruce(){
        // given
        GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");

        // when
        SimpleDriverDataSource userDao = applicationContext.getBean("dataSource", SimpleDriverDataSource.class);

        // then
        assertThatNoException();
    }

}
