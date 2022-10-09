package ru.practicum.explorewithme.main.repository;

import java.time.LocalDateTime;
import java.util.List;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventState;

public interface EventCustomRepository {

    List<Event> searchForEvents(
        String text,
        List<Long> categories,
        Boolean paid,
        LocalDateTime rangeStart,
        LocalDateTime rangeEnd,
        Boolean onlyAvailable,
        String sort,
        int from,
        int size
    );

    List<Event> searchForEvents(
        List<Long> users,
        List<EventState> states,
        List<Long> categories,
        LocalDateTime rangeStart,
        LocalDateTime rangeEnd,
        int from,
        int size
    );
}
