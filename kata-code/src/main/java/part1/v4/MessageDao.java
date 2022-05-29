package part1.v4;

public class MessageDao {

    private ConnectionMaker connectionMaker;
    public MessageDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public MessageDao(part1.v5.ConnectionMaker connectionMaker) {

    }
}
