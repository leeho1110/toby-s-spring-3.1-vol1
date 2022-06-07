package part3.part1.getbean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import part1.v5.DaoFactory;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationContextGetBeanTest {

    @DisplayName("getBean(String name, Class<T> requiredType)의 requiredType에 객체 타입이 잘못 명시된 경우 BeanNotOfRequiredTypeException 예외 발생")
    @Test
    public void test_when_getBean_2st_param_is_wrong_then_return_BeanNotOfRequiredTypeException(){
        // given
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        // when & then
        assertThatThrownBy(() -> {
            applicationContext.getBean("getBornTobeBean", DaoFactory.class);
        }).isInstanceOf(BeanNotOfRequiredTypeException.class);
    }

    @DisplayName("getBean(Stirng name)의 캐스팅 타입이 잘못된 경우 ClassCastException 예외 발생")
    @Test
    public void test_when_getBean_casting_is_wrong_then_return_ClassCastException(){
        // given
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        // when & then
        assertThatThrownBy(() -> {
            DaoFactory getBornTobeBean = (DaoFactory) applicationContext.getBean("getBornTobeBean");
        }).isInstanceOf(ClassCastException.class);
    }
}
