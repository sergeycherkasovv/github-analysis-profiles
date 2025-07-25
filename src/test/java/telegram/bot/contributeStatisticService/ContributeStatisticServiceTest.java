package telegram.bot.contributeStatisticService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telegram.bot.services.contributeStatisticService.ContributeStatisticService;
import telegram.bot.util.FileReaderForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContributeStatisticServiceTest {
    private ContributeStatisticService service;
    private String directory;

    @BeforeEach
    void setUp() {
        service = new ContributeStatisticService();
        directory = "src/test/resources/fixtures/contributeStatistic/";
    }

    @Test
    void create() throws Exception {
        var file = FileReaderForTest.readJsonNodeFromFile(directory + "create.json");
        var contribute = service.create(file);

        assertEquals(25, contribute.getCodeReviews());
        assertEquals(25, contribute.getIssues());
        assertEquals(25, contribute.getPullRequest());
        assertEquals(25, contribute.getTotalCommits());
    }

    @Test
    void crateZeroCount() throws Exception {
        var file = FileReaderForTest.readJsonNodeFromFile(directory + "zeroCounts.json");
        var contribute = service.create(file);

        assertEquals(0, contribute.getCodeReviews());
        assertEquals(0, contribute.getIssues());
        assertEquals(0, contribute.getPullRequest());
        assertEquals(0, contribute.getTotalCommits());
    }
}
