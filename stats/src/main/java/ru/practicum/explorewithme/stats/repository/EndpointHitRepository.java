package ru.practicum.explorewithme.stats.repository;

import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.stats.model.EndpointHit;

public interface EndpointHitRepository extends CrudRepository<EndpointHit, Long>,
    CustomEndpointHitRepository {

}
