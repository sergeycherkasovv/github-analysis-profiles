package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.MetricsRepositories;

import java.util.stream.StreamSupport;

@Service
public class MetricsRepositoriesService {
    public MetricsRepositories createMetricsRepositories(JsonNode username) {
        var metricsRepositories = new MetricsRepositories();

        metricsRepositories.setPublicRepos(username.path("totalRepositories").path("totalCount").asInt(0));
        metricsRepositories.setStarredRepoCount(username.path("starredRepositories").path("totalCount").asInt(0));

        JsonNode repositoriesNodes = username.path("totalRepositories").path("nodes");

        var forks = repositoriesNodes.isArray()
                    ? StreamSupport
                    .stream(repositoriesNodes.spliterator(), true)
                    .mapToInt(repo -> repo.path("forkCount").asInt(0))
                    .sum()
                    : 0;
        metricsRepositories.setForkCount(forks);

        var stargazer = repositoriesNodes.isArray()
                    ? StreamSupport
                    .stream(repositoriesNodes.spliterator(), true)
                    .mapToInt(repo -> repo.path("stargazerCount").asInt(0))
                    .sum()
                    : 0;

        metricsRepositories.setStargazerCount(stargazer);

        return metricsRepositories;
    }
}
