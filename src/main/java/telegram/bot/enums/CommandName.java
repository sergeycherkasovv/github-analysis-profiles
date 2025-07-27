package telegram.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandName {
    START("/start"),
    STATS("/stats"),
    THERE_IS_NO_SUCH_COMMAND("/.*");

    private final String message;
}
