package ru.practicum.explorewithme.main.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.main.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>, EventCustomRepository {

    List<Event> findEventsByInitiator_Id(long userId, Pageable pageable);
}
