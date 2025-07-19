package telegram.bot.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import telegram.bot.exception.GitHubAPIException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GitHubAPIException.class)
    public String handleGitHubAPIException(GitHubAPIException ex) {
        return ex.getMessage();
    }
}
