package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telegram.bot.util.FileReaderForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricsRepositoriesServiceTest {
    private MetricsRepositoriesService service;
    private String directory;
    private ObjectMapper om;
    private FileReaderForTest read;

    @BeforeEach
    void setUp() {
        read = new FileReaderForTest();
        om = new ObjectMapper();
        service = new MetricsRepositoriesService();
        directory = "src/test/resources/fixtures/metricsRepositories/";
    }

    @Test
    void create() throws Exception {
        var file = read.readJsonNodeFromFile(directory + "create.json");
        var json = om.readTree(file);
        var metrics = service.create(json);

        assertEquals(25, metrics.getPublicRepos());
        assertEquals(15, metrics.getForkCount());
        assertEquals(5, metrics.getStargazerCount());
        assertEquals(1, metrics.getStarredRepoCount());
    }

    @Test
    void createZeroCount() throws Exception {
        var file = read.readJsonNodeFromFile(directory + "zeroCounts.json");
        var json = om.readTree(file);
        var metrics = service.create(json);

        assertEquals(0, metrics.getPublicRepos());
        assertEquals(0, metrics.getForkCount());
        assertEquals(0, metrics.getStargazerCount());
        assertEquals(0, metrics.getStarredRepoCount());
    }
}
