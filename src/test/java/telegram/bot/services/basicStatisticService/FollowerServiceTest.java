package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telegram.bot.util.FileReaderForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FollowerServiceTest {
    private FollowerService service;
    private String directory;
    private ObjectMapper om;
    private FileReaderForTest read;

    @BeforeEach
    void setUp() {
        read = new FileReaderForTest();
        om = new ObjectMapper();
        service = new FollowerService();
        directory = "src/test/resources/fixtures/follower/";
    }

    @Test
    void create() throws Exception {

        var file = read.readJsonNodeFromFile(directory + "create.json");
        var json = om.readTree(file);
        var follower = service.create(json);

        assertEquals(10, follower.getFollowers());
        assertEquals(5, follower.getFollowing());
    }

    @Test
    void createZeroCounts() throws Exception {

        var file = read.readJsonNodeFromFile(directory + "zeroCounts.json");
        var json = om.readTree(file);
        var follower = service.create(json);

        assertEquals(0, follower.getFollowers());
        assertEquals(0, follower.getFollowing());
    }
}
