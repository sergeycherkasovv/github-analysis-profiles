package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.BasicStatistic;

@Service
@AllArgsConstructor
public class BasicStatisticService {
    private final FollowerService followerService;
    private final UserService userService;
    private final MetricsRepositoriesService metricsRepositoriesService;

    @SneakyThrows
    public BasicStatistic createBasicStatistic(JsonNode username) {
        var profile = new BasicStatistic();

        JsonNode user = username.path("data").path("user");

        profile.setUser(userService.createUser(user));
        profile.setFollower(followerService.createFollower(user));
        profile.setMetricsRepositories(metricsRepositoriesService.createMetricsRepositories(user));

        return profile;
    }
}
