package telegram.bot.services.basicStatisticService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telegram.bot.util.FileReaderForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FollowerServiceTest {
    private FollowerService service;
    private String directory;

    @BeforeEach
    void setUp() {
        service = new FollowerService();
        directory = "src/test/resources/fixtures/follower/";
    }

    @Test
    void create() throws Exception{

        var file = FileReaderForTest.readJsonNodeFromFile(directory + "create.json");
        var follower = service.create(file);

        assertEquals(10, follower.getFollowers());
        assertEquals(5, follower.getFollowing());
    }

    @Test
    void createZeroCounts() throws Exception {

        var file = FileReaderForTest.readJsonNodeFromFile(directory + "zeroCounts.json");
        var follower = service.create(file);

        assertEquals(0, follower.getFollowers());
        assertEquals(0, follower.getFollowing());
    }
}
