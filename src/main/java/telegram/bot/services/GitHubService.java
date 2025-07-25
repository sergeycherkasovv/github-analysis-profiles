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

import static telegram.bot.enums.GraphQlFileNames.BASIC_STATISTIC;
import static telegram.bot.enums.GraphQlFileNames.CONTRIBUTIONS_STATISTIC;
import static telegram.bot.enums.GraphQlFileNames.CREATED_AT_PROFILE;

@Slf4j
@Service
@AllArgsConstructor
public class GitHubService {
    private final WebClient gitHubWebClientConfig;
    private final ContributeStatisticService contributeService;
    private final GraphQlReader graphQlReader;
    private final BasicStatisticService basicStatisticService;

    private static final int TIMEOUT = 10;

    public BasicStatistic fetchBasicStatistic(String usernameGitHub) {
        var query = graphQlReader.reading(BASIC_STATISTIC.getMessage());

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", Map.of("login", usernameGitHub)
        );

        return gitHubWebClientConfig.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(TIMEOUT))
                .map(basicStatisticService::create)
                .doOnError(error -> log.error("GitHub API Exception: {}", error.getMessage()))
                .onErrorMap(error -> new GitHubAPIException(""))
                .block();
    }

    public ContributeStatistic fetchContributeStatistic(String usernameGitHub) {
        return fetchCreatedAt(usernameGitHub)
                .flatMapMany(createdAtStr -> {
                    Instant createdAt = Instant.parse(createdAtStr);
                    var startYear = createdAt.atZone(ZoneOffset.UTC).getYear();
                    var endYear = ZonedDateTime.now(ZoneOffset.UTC).getYear();

                    return Flux
                            .range(startYear, endYear - startYear + 1)
                            .flatMap(year -> fetchContributionsForYear(usernameGitHub, year));
                })
                .reduce(new ContributeStatistic(), (acc, current) -> {
                    acc.add(current);
                    return acc;
                })
                .block();
    }

    private Mono<String> fetchCreatedAt(String usernameGitHub) {
        var query = graphQlReader.reading(CREATED_AT_PROFILE.getMessage());

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", Map.of("login", usernameGitHub)
        );

        return gitHubWebClientConfig.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(TIMEOUT))
                .map(json -> json.path("data").path("user").path("createdAt").asText())
                .doOnError(error -> log.error("GitHub API Exception: {}", error.getMessage()))
                .onErrorMap(error -> new GitHubAPIException(""));
    }

    private Mono<ContributeStatistic> fetchContributionsForYear(String usernameGitHub, int year) {
        var query = graphQlReader.reading(CONTRIBUTIONS_STATISTIC.getMessage());
        var from = ZonedDateTime
                .of(year, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)
                .toInstant()
                .toString();
        var to = ZonedDateTime
                .of(year, 12, 31, 23, 59, 59, 999_999_999, ZoneOffset.UTC)
                .toInstant()
                .toString();

        Map<String, Object> variables = Map.of(
                "login", usernameGitHub,
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
                .map(contributeService::create)
                .doOnError(error -> log.error("GitHub API Exception: {}", error.getMessage()))
                .onErrorMap(error -> new GitHubAPIException(""));
    }
}
