package ru.practicum.explorewithme.main.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventState;

public interface EventRepository extends CrudRepository<Event, Long>, EventCustomRepository {

    List<Event> findEventsByInitiator_Id(long userId, Pageable pageable);

    List<Event> findEventsByIdIn(Set<Long> ids);

    Optional<Event> findByIdAndState(Long eventId, EventState state);

    List<Event> findEventsByCategory_Id(long categoryId);
}
