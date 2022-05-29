package part1.v5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    // 생성될 빈의 이름은 메서드 이름과 동일
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    private ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public UserDao userDaoWithNConnection() {
        return new UserDao(connectionMakerOfNCompany());
    }

    private ConnectionMaker connectionMakerOfNCompany() {
        return new NConnectionMaker();
    }
}
