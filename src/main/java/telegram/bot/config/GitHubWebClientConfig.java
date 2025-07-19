package telegram.bot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import telegram.bot.component.GitHubProperties;


@Configuration
@AllArgsConstructor
public class GitHubWebClientConfig {
    private final GitHubProperties gitHubProperties;
    private final WebClient.Builder webClientBuilder;

    @Bean
    public WebClient gitHubWebClient() {
        return webClientBuilder
                .baseUrl(gitHubProperties.getUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + gitHubProperties.getToken())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
