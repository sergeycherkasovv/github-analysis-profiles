package telegram.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GraphQlFileNames {
    BASIC_STATISTIC("basicStatistic"),
    CREATED_AT_PROFILE("createdAtProfile"),
    CONTRIBUTIONS_STATISTIC("contributionsStatistic");

    private final String message;
}
