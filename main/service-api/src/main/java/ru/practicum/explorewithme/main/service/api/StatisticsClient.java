package ru.practicum.explorewithme.main.service.api;

public interface StatisticsClient {

    void saveEndpointHit(String uri, String ip);
}
