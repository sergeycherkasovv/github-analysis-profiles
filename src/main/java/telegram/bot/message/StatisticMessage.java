package telegram.bot.message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import telegram.bot.services.GitHubService;
import telegram.bot.util.MarkdownV2Util;
import telegram.bot.util.NormalizeUsername;

@Service
@AllArgsConstructor
public class StatisticMessage {
    private final GitHubService gitHubService;
    private final NormalizeUsername normalizeUsername;
    private final MarkdownV2Util markdownV2Util;

    public String getMessage(String message) {
        var username = normalizeUsername.normalized(message);
        var basicProfileStatistic = gitHubService.fetchBasicStatistic(username).block();
        var contributesStatistic = gitHubService.fetchContributeStatistic(username).block();

        var email = markdownV2Util.escapeMarkdownV2(basicProfileStatistic.getUser().getEmail());
        var user = markdownV2Util.escapeMarkdownV2(username);
        var fullName = markdownV2Util.escapeMarkdownV2(basicProfileStatistic.getUser().getFullname());

        return """
         👤
         ***Profile:*** [%s](%s)
         ***FullName:*** %s
         ***Email:*** %s
         ***Created at:*** %s
         📊
         ***Public Repositories:*** %d
         ***Commits:*** %d
         ***Forks:*** %d
         ***Issues:*** %d
         ***Pull Requests:*** %d
         ***Code Reviews:*** %d
         ⭐
         ***Followers:*** %d
         ***Following:*** %d
         ***Stars:*** %d
         ***Stargazer:*** %d
         """.formatted(
                user,
                basicProfileStatistic.getUser().getProfileURL(),
                fullName,
                email,
                basicProfileStatistic.getUser().getCreatedAt(),
                basicProfileStatistic.getMetricsRepositories().getPublicRepos(),
                contributesStatistic.getTotalCommits(),
                basicProfileStatistic.getMetricsRepositories().getForkCount(),
                contributesStatistic.getIssues(),
                contributesStatistic.getPullRequest(),
                contributesStatistic.getCodeReviews(),
                basicProfileStatistic.getFollower().getFollowers(),
                basicProfileStatistic.getFollower().getFollowing(),
                basicProfileStatistic.getMetricsRepositories().getStarredRepoCount(),
                basicProfileStatistic.getMetricsRepositories().getStargazerCount()
        );
    }
}
