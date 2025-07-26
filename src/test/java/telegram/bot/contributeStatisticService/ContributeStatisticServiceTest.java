package telegram.bot.contributeStatisticService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telegram.bot.services.contributeStatisticService.ContributeStatisticService;
import telegram.bot.util.FileReaderForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContributeStatisticServiceTest {
    private ContributeStatisticService service;
    private String directory;
    private ObjectMapper om;
    private FileReaderForTest read;

    @BeforeEach
    void setUp() {
        read = new FileReaderForTest();
        om = new ObjectMapper();
        service = new ContributeStatisticService();
        directory = "src/test/resources/fixtures/contributeStatistic/";
    }

    @Test
    void create() throws Exception {
        var file = read.readJsonNodeFromFile(directory + "create.json");
        var json = om.readTree(file);
        var contribute = service.create(json);

        assertEquals(25, contribute.getCodeReviews());
        assertEquals(25, contribute.getIssues());
        assertEquals(25, contribute.getPullRequest());
        assertEquals(25, contribute.getTotalCommits());
    }

    @Test
    void crateZeroCount() throws Exception {
        var file = read.readJsonNodeFromFile(directory + "zeroCounts.json");
        var json = om.readTree(file);
        var contribute = service.create(json);

        assertEquals(0, contribute.getCodeReviews());
        assertEquals(0, contribute.getIssues());
        assertEquals(0, contribute.getPullRequest());
        assertEquals(0, contribute.getTotalCommits());
    }
}
