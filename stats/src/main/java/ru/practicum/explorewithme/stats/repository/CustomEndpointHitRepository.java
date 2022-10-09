package ru.practicum.explorewithme.stats.repository;

import java.time.LocalDateTime;
import java.util.List;
import ru.practicum.explorewithme.stats.model.ViewsStats;

public interface CustomEndpointHitRepository {

    List<ViewsStats> findStats(LocalDateTime start, LocalDateTime end, List<String> uris,
        boolean unique);
}
