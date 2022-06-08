package part5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import part4.UserDaoJdbc;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private User user1;
    private User user2;
    private User user3;

    private part4.UserDaoJdbc userDaoJdbc = new UserDaoJdbc();

    @BeforeEach
    public void setUp(){
        this.user1 = new User("leeho1", "일호","lh1", Level.BASIC,1,0);
        this.user2 = new User("leeho2", "이호","lh2", Level.SILVER,55,10);
        this.user3 = new User("leeho3", "삼호","lh3", Level.GOLD,100,40);
    }

    @Test
    public void addAndGet(){
        // given
        userDaoJdbc.add(new part1.v1.User());

        // when


        // then
    }

    private void checkSameUser(User user1, User user2){
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
    }
}
