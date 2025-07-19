package telegram.bot.dto.basicStatistic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricsRepositories {
    private Integer publicRepos;
    private Integer starredRepoCount;
    private Integer forkCount;
    private Integer stargazerCount;
}
