package part1.v6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){
        System.out.println("call userDao Bean");
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        System.out.println("call connectionMaker Bean");
        return new CountingConnectionMaker(realConnectionMaker());
    }
    @Bean
    public ConnectionMaker realConnectionMaker(){
        System.out.println("call realConnectionMaker Bean");
        return new DConnectionMaker();
    }
}
