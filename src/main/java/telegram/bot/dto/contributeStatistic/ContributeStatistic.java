package telegram.bot.dto.contributeStatistic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContributeStatistic {
    private long totalCommits = 0;
    private long pullRequest = 0;
    private long issues = 0;
    private long codeReviews = 0;

    public void add(ContributeStatistic other) {
        this.totalCommits += other.totalCommits;
        this.pullRequest += other.pullRequest;
        this.issues += other.issues;
        this.codeReviews += other.codeReviews;
    }
}
