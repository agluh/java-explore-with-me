package ru.practicum.explorewithme.stats.service;

import java.util.List;
import ru.practicum.explorewithme.stats.model.ViewsStats;
import ru.practicum.explorewithme.stats.model.EndpointHit;

public interface StatsService {

    EndpointHit recordHit(String app, String uri, String ip, long timestamp);

    List<ViewsStats> findStats(long start, long end, List<String> uris, boolean unique);
}
