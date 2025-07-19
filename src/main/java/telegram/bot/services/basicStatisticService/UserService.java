package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.User;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;

import static telegram.bot.enums.Message.NOT_PUBLIC;


@Service
public class UserService {
    public User createUser(JsonNode username) throws MalformedURLException {
        var user = new User();

        user.setFullname(username.path("name").asText(NOT_PUBLIC.getMessage()));
        user.setEmail(username.path("email").asText(NOT_PUBLIC.getMessage()));
        user.setCreatedAt(Date.from(Instant.parse(username.path("createdAt").asText(NOT_PUBLIC.getMessage()))));
        user.setProfileURL(new URL(username.path("url").asText(NOT_PUBLIC.getMessage())));

        return user;
    }
}
