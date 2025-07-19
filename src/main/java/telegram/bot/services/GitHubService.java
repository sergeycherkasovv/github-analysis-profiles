package telegram.bot.services;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import telegram.bot.dto.contributeStatistic.ContributeStatistic;
import telegram.bot.dto.basicStatistic.BasicStatistic;
import telegram.bot.exception.GitHubAPIException;
import telegram.bot.services.basicStatisticService.BasicStatisticService;
import telegram.bot.services.contributeStatisticService.ContributeStatisticService;
import telegram.bot.util.GraphQlReader;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class GitHubService {
    private static final int TIMEOUT = 10;
    private final WebClient gitHubWebClientConfig;
    private final ContributeStatisticService contributeService;
    private final GraphQlReader graphQlReader;
    private final BasicStatisticService basicStatisticService;

    public Mono<BasicStatistic> fetchBasicStatistic(String username) {
        var query = graphQlReader.reading("basicStatistic");

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", Map.of("login", username)
        );

        return gitHubWebClientConfig.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(TIMEOUT))
                .map(basicStatisticService::createBasicStatistic)
                .doOnError(error -> log.error("GitHub API Exception: {}", error.getMessage()))
                .onErrorMap(error -> new GitHubAPIException("GitHub API Exception"));
    }

    public Mono<ContributeStatistic> fetchContributeStatistic(String username) {
        return fetchCreatedAt(username)
                .flatMapMany(createdAtStr -> {
                    Instant createdAt = Instant.parse(createdAtStr);
                    var startYear = createdAt.atZone(ZoneOffset.UTC).getYear();
                    var endYear = ZonedDateTime.now(ZoneOffset.UTC).getYear();

                    return Flux
                            .range(startYear, endYear - startYear + 1)
                            .flatMap(year -> fetchContributionsForYear(username, year));
                })
                .reduce(new ContributeStatistic(), (acc, current) -> {
                    acc.add(current);
                    return acc;
                });
    }

    private Mono<String> fetchCreatedAt(String username) {
        var query = graphQlReader.reading("createdAtProfile");

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", Map.of("login", username)
        );

        return gitHubWebClientConfig.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(TIMEOUT))
                .map(json -> json.path("data").path("user").path("createdAt").asText())
                .doOnError(error -> log.error("GitHub API Exception: {}", error.getMessage()))
                .onErrorMap(error -> new GitHubAPIException("GitHub API Exception"));
    }

    private Mono<ContributeStatistic> fetchContributionsForYear(String username, int year) {
        var query = graphQlReader.reading("contributionsStatistic");
        var from = ZonedDateTime
                .of(year, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)
                .toInstant()
                .toString();
        var to = ZonedDateTime
                .of(year, 12, 31, 23, 59, 59, 999_999_999, ZoneOffset.UTC)
                .toInstant()
                .toString();

        Map<String, Object> variables = Map.of(
                "login", username,
                "from", from,
                "to", to
        );

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", variables
        );

        return gitHubWebClientConfig.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(TIMEOUT))
                .map(contributeService::createContributeStatistic)
                .doOnError(error -> log.error("GitHub API Exception: {}", error.getMessage()))
                .onErrorMap(error -> new GitHubAPIException("GitHub API Exception"));
    }
}
