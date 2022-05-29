package part1.v4;

public class AccountDao {

    private ConnectionMaker connectionMaker;
    public AccountDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
