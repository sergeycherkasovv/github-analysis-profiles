package telegram.bot.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.BasicStatistic;
import telegram.bot.dto.contributeStatistic.ContributeStatistic;
import telegram.bot.util.MarkdownV2Util;
import telegram.bot.util.NormalizeUsername;

import java.util.regex.Pattern;

import static telegram.bot.enums.CommandName.START;
import static telegram.bot.enums.CommandName.THERE_IS_NO_SUCH_COMMAND;
import static telegram.bot.enums.Message.NO_COMMAND;
import static telegram.bot.enums.Message.START_MESSAGE;
import static telegram.bot.enums.Message.STATISTIC_MESSAGE;

@Service
@AllArgsConstructor
public class MessageService {
    private final MarkdownV2Util markdownV2Util;
    private final NormalizeUsername normalizeUsername;

    public String generateMessage(String message, GitHubService gitHubService) {
        var theIsNoSuchCommand = Pattern.compile(THERE_IS_NO_SUCH_COMMAND.getMessage());

        if (START.getMessage().equals(message)) {
            return markdownV2Util.escapeMarkdownV2(START_MESSAGE.getMessage());
        }
        if (theIsNoSuchCommand.matcher(message).matches()) {
            return markdownV2Util.escapeMarkdownV2(NO_COMMAND.getMessage());
        }

        var usernameGitHub = normalizeUsername.normalized(message);
        var basicStatistic = gitHubService.fetchBasicStatistic(usernameGitHub);
        var contributesStatistic = gitHubService.fetchContributeStatistic(usernameGitHub);

        return formatStatisticMessage(usernameGitHub, basicStatistic, contributesStatistic);

    }

    private String formatStatisticMessage(String usernameGitHub,
                                          BasicStatistic basicStatistic,
                                          ContributeStatistic contributeStatistic) {

        return STATISTIC_MESSAGE.getMessage()
                .formatted(
                        markdownV2Util.escapeMarkdownV2(usernameGitHub),
                        basicStatistic.getUser().getProfileURL(),
                        markdownV2Util.escapeMarkdownV2(basicStatistic.getUser().getFullName()),
                        markdownV2Util.escapeMarkdownV2(basicStatistic.getUser().getEmail()),
                        basicStatistic.getUser().getCreatedAt(),
                        basicStatistic.getMetricsRepositories().getPublicRepos(),
                        contributeStatistic.getTotalCommits(),
                        basicStatistic.getMetricsRepositories().getForkCount(),
                        contributeStatistic.getIssues(),
                        contributeStatistic.getPullRequest(),
                        contributeStatistic.getCodeReviews(),
                        basicStatistic.getFollower().getFollowers(),
                        basicStatistic.getFollower().getFollowing(),
                        basicStatistic.getMetricsRepositories().getStarredRepoCount(),
                        basicStatistic.getMetricsRepositories().getStargazerCount()
                );
    }
}