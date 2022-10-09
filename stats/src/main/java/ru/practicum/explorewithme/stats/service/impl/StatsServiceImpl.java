package ru.practicum.explorewithme.stats.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.stats.model.ViewsStats;
import ru.practicum.explorewithme.stats.model.EndpointHit;
import ru.practicum.explorewithme.stats.repository.EndpointHitRepository;
import ru.practicum.explorewithme.stats.service.StatsService;

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final EndpointHitRepository repository;

    @Override
    public EndpointHit recordHit(String app, String uri, String ip, long timestamp) {
        EndpointHit hit = new EndpointHit(null, app, uri, ip,
            LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC));

        repository.save(hit);

        return hit;
    }

    @Override
    public List<ViewsStats> findStats(long start, long end, List<String> uris, boolean unique) {
        return repository.findStats(
            LocalDateTime.ofEpochSecond(start, 0, ZoneOffset.UTC),
            LocalDateTime.ofEpochSecond(end, 0, ZoneOffset.UTC),
            uris,
            unique
        );
    }
}
