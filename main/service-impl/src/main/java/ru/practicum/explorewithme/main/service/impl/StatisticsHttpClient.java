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

    private String statsUrl;

    private HttpClient client;

    public StatisticsHttpClient(@Value("${app.stats.url}") String statsUrl) {
        this.statsUrl = statsUrl;
        client = HttpClient.newBuilder().build();
    }

    @Override
    @SneakyThrows
    public void saveEndpointHit(String uri, String ip) {
        Hit hit = new Hit(
            "main",
            uri,
            ip,
            LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        );

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(hit);

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
