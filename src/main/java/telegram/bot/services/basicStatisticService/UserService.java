package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.User;

import java.net.MalformedURLException;

import static telegram.bot.enums.Message.NOT_PUBLIC;


@Service
public class UserService {
    public User create(JsonNode usernameGitHub) throws MalformedURLException {
        var user = new User();

        user.setFullName(usernameGitHub.path("name").asText(NOT_PUBLIC.getMessage()));
        user.setEmail(usernameGitHub.path("email").asText(NOT_PUBLIC.getMessage()));
        user.setCreatedAt(usernameGitHub.path("createdAt").asText(NOT_PUBLIC.getMessage()));
        user.setProfileURL(usernameGitHub.path("url").asText(NOT_PUBLIC.getMessage()));

        return user;
    }
}
