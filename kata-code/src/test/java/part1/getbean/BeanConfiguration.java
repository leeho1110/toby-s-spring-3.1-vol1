package part1.getbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BornTobeBean getBornTobeBean(){
        return new BornTobeBean();
    }
}
