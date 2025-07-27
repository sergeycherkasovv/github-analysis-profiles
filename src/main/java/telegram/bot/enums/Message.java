package telegram.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    NOT_PUBLIC("not public"),
    NO_COMMAND("\uD83E\uDD14 Hmm, I don’t recognize that command. Maybe you wanted to type /start"),
    ERROR_MESSAGE("\uD83D\uDE2E Oops, something went wrong. Please try again."),
    USER_COUNT_STATISTIC("We are already: "),
    START_MESSAGE("""
                Hi! 👋
                I can show you a quick
                overview of a GitHub profile.
                Just send:
                - username,
                - @username,
                - https://github.com/username.
                Ready to give it a try?
                """),

    STATISTIC_MESSAGE("""
         👤
         ***Profile:*** [%s](%s)
         ***FullName:*** %s
         ***Email:*** %s
         ***Created:*** %s
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
         ***Stargazers:*** %d
         """);

    private final String message;
}
