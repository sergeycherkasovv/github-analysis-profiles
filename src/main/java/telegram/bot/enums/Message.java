package telegram.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    NOT_PUBLIC("not public"),
    NOT_COMMAND("\uD83E\uDD14 Hmm, I don’t recognize that command. Maybe you wanted to type '/start'"),
    EXCEPTION("\uD83D\uDE2E Oops, something went wrong. Please try again."),
    START_MESSAGE("""
                Hi! 👋
                I can show you a quick
                overview of a GitHub profile.
                Just send:
                - username,
                - @username,
                - https://github.com/username.
                Ready to give it a try?
                """);

    private final String message;
}
