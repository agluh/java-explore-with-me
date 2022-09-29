package ru.practicum.explorewithme.main.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.main.service.api.StatisticsClient;

@Service
public class StatisticsHttpClient implements StatisticsClient {

    private final String statsUrl;
    private final String appName;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public StatisticsHttpClient(
        @Value("${app.stats.url}") String statsUrl,
        @Value("${app.name}") String appName,
        ObjectMapper objectMapper
    ) {
        this.statsUrl = statsUrl;
        this.appName = appName;
        this.objectMapper = objectMapper;
        client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(2))
            .build();
    }

    @Override
    @SneakyThrows
    public void saveEndpointHit(String uri, String ip) {
        Hit hit = new Hit(
            appName,
            uri,
            ip,
            LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        );

        String requestBody = objectMapper.writeValueAsString(hit);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(statsUrl + "/hit"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .timeout(Duration.ofSeconds(5))
            .build();

        client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .join();
    }

    @AllArgsConstructor
    @Getter
    private static class Hit {

        private String app;

        private String uri;

        private String ip;

        private Long timestamp;
    }
}
