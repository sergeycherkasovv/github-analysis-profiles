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
    public BasicStatistic create(JsonNode usernameGitHub) {
        var profile = new BasicStatistic();

        JsonNode user = usernameGitHub.path("data").path("user");

        profile.setUser(userService.create(user));
        profile.setFollower(followerService.create(user));
        profile.setMetricsRepositories(metricsRepositoriesService.create(user));

        return profile;
    }
}
