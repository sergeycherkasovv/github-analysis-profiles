package telegram.bot.dto.basicStatistic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicStatistic {
    private User user;
    private MetricsRepositories metricsRepositories;
    private Follower follower;
}
