package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.MetricsRepositories;

import java.util.stream.StreamSupport;

@Service
public class MetricsRepositoriesService {
    public MetricsRepositories create(JsonNode usernameGitHub) {
        var metricsRepositories = new MetricsRepositories();

        metricsRepositories.setPublicRepos(usernameGitHub.path("totalRepositories").path("totalCount").asInt(0));
        metricsRepositories.setStarredRepoCount(usernameGitHub.path("starredRepositories").path("totalCount").asInt(0));

        JsonNode repositoriesNodes = usernameGitHub.path("totalRepositories").path("nodes");

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
