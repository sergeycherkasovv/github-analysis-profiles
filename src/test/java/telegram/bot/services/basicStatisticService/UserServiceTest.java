package telegram.bot.services.basicStatisticService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telegram.bot.util.FileReaderForTest;

import static org.assertj.core.api.Assertions.assertThat;
import static telegram.bot.enums.Message.NOT_PUBLIC;

public class UserServiceTest {
    private UserService service;
    private String directory;

    @BeforeEach
    void setUp() {
        service = new UserService();
        directory = "src/test/resources/fixtures/user/";
    }

    @Test
    void create() throws Exception{
        var file = FileReaderForTest.readJsonNodeFromFile(directory + "create.json");
        var user = service.create(file);

        assertThat(user.getFullName()).isEqualTo("Mister Smith");
        assertThat(user.getEmail()).isEqualTo("mister.smith@example.com");
        assertThat(user.getCreatedAt()).isEqualTo("01 July 2025");
        assertThat(user.getProfileURL()).isEqualTo("https://github.com/mistersmith");
    }

    @Test
    void createNotPublic() throws Exception {
        var file = FileReaderForTest.readJsonNodeFromFile(directory + "notPublic.json");
        var user = service.create(file);

        assertThat(user.getFullName()).isEqualTo(NOT_PUBLIC.getMessage());
        assertThat(user.getEmail()).isEqualTo(NOT_PUBLIC.getMessage());
        assertThat(user.getCreatedAt()).isEqualTo(NOT_PUBLIC.getMessage());
        assertThat(user.getProfileURL()).isEqualTo(NOT_PUBLIC.getMessage());
    }
}
