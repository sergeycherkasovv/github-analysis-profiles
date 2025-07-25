package telegram.bot.services.contributeStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import telegram.bot.dto.contributeStatistic.ContributeStatistic;

@Service
public class ContributeStatisticService {
    public ContributeStatistic create(JsonNode usernameGitHub) {
        var contribute = new ContributeStatistic();

        JsonNode data = usernameGitHub.path("data").path("user").path("contributionsCollection");

        contribute.setTotalCommits(data.path("totalCommitContributions").asLong(0));
        contribute.setPullRequest(data.path("totalPullRequestContributions").asLong(0));
        contribute.setCodeReviews(data.path("totalPullRequestReviewContributions").asLong(0));
        contribute.setIssues(data.path("totalIssueContributions").asLong(0));

        return contribute;
    }
}
