package ru.practicum.explorewithme.main.service.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventCategory;
import ru.practicum.explorewithme.main.model.EventState;
import ru.practicum.explorewithme.main.model.User;
import ru.practicum.explorewithme.main.repository.EventRepository;
import ru.practicum.explorewithme.main.service.api.CategoryService;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.service.api.UserService;
import ru.practicum.explorewithme.main.service.api.contract.AdminUpdateEventRequest;
import ru.practicum.explorewithme.main.service.api.contract.NewEventRequest;
import ru.practicum.explorewithme.main.service.api.contract.UpdateEventRequest;
import ru.practicum.explorewithme.main.service.api.exception.CategoryNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.EventIsNotAbleToChangeException;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.TooLittleTimeLeftBeforeEventStartException;
import ru.practicum.explorewithme.main.service.api.exception.UserIsNotAnInitiatorOfEventException;
import ru.practicum.explorewithme.main.service.api.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventRepository repository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final Clock clock;

    @Override
    @Transactional
    public Event addEvent(NewEventRequest request) {
        User initiator = userService.findUser(request.getInitiator()).orElseThrow(() ->
            new UserNotFoundException("not found"));

        EventCategory category = categoryService.findCategory(request.getCategory())
            .orElseThrow(() -> new CategoryNotFoundException("not found"));

        LocalDateTime pubDate = LocalDateTime.now(clock);
        LocalDateTime deadline = pubDate.plus(2, ChronoUnit.HOURS);

        if (request.getEventDate().isBefore(deadline)) {
            throw new TooLittleTimeLeftBeforeEventStartException("too little time before event");
        }

        Event event = request.toModel();
        event.setCategory(category);
        event.setInitiator(initiator);
        event.setCreatedOn(LocalDateTime.now(clock));

        repository.save(event);

        return event;
    }

    @Override
    @Transactional
    public Event updateEvent(UpdateEventRequest request) {
        Event event = repository.findById(request.getEventId()).orElseThrow(()
            -> new EventNotFoundException("not found"));

        User requester = userService.findUser(request.getRequester()).orElseThrow(() ->
            new UserNotFoundException("not found"));

        if (!event.getInitiator().equals(requester)) {
            throw new UserIsNotAnInitiatorOfEventException("user is not an initiator of event");
        }

        if (event.getState() == EventState.PUBLISHED) {
            throw new EventIsNotAbleToChangeException("event is published");
        }

        if (request.getTitle() != null) {
            event.setTitle(request.getTitle());
        }

        if (request.getAnnotation() != null) {
            event.setAnnotation(request.getAnnotation());
        }

        if (request.getDescription() != null) {
            event.setDescription(request.getDescription());
        }

        if (request.getCategory() != null) {
            EventCategory category = categoryService.findCategory(request.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException("not found"));

            event.setCategory(category);
        }

        if (request.getEventDate() != null) {
            if (request.getEventDate().isBefore(LocalDateTime.now(clock).plusHours(2))) {
                throw new TooLittleTimeLeftBeforeEventStartException("too little time before event");
            }

            event.setEventDate(request.getEventDate());
        }

        if (request.getPaid() != null) {
            event.setPaid(request.getPaid());
        }

        if (request.getParticipantLimit() != null) {
            event.setParticipantLimit(request.getParticipantLimit());
        }

        repository.save(event);

        return event;
    }

    @Override
    @Transactional
    public Event updateEvent(AdminUpdateEventRequest request) {
        Event event = repository.findById(request.getEventId()).orElseThrow(()
            -> new EventNotFoundException("not found"));

        if (request.getTitle() != null) {
            event.setTitle(request.getTitle());
        }

        if (request.getAnnotation() != null) {
            event.setAnnotation(request.getAnnotation());
        }

        if (request.getDescription() != null) {
            event.setDescription(request.getDescription());
        }

        if (request.getCategory() != null) {
            EventCategory category = categoryService.findCategory(request.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException("not found"));

            event.setCategory(category);
        }

        if (request.getEventDate() != null) {
            event.setEventDate(request.getEventDate());
        }

        if (request.getLocation() != null) {
            event.setLocation(request.getLocation());
        }

        if (request.getPaid() != null) {
            event.setPaid(request.getPaid());
        }

        if (request.getParticipantLimit() != null) {
            event.setParticipantLimit(request.getParticipantLimit());
        }

        if (request.getRequestModeration() != null) {
            event.setRequestModeration(request.getRequestModeration());
        }

        repository.save(event);

        return event;
    }

    @Override
    @Transactional
    public Event cancelEvent(long userId, long eventId) {
        Event event = repository.findById(eventId).orElseThrow(() ->
            new EventNotFoundException("not found"));

        User requester = userService.findUser(userId).orElseThrow(() ->
            new UserNotFoundException("not found"));

        if (!event.getInitiator().equals(requester)) {
            throw new UserIsNotAnInitiatorOfEventException("user is not an initiator of event");
        }

        if (event.getState() != EventState.PENDING) {
            throw new EventIsNotAbleToChangeException("event is not in pending state");
        }

        event.setState(EventState.CANCELED);

        repository.save(event);

        return event;
    }

    @Override
    @Transactional
    public Event publishEvent(long eventId) {
        Event event = repository.findById(eventId).orElseThrow(() ->
            new EventNotFoundException("not found"));

        if (event.getState() != EventState.PENDING) {
            throw new EventIsNotAbleToChangeException("event is not in pending state");
        }

        LocalDateTime pubDate = LocalDateTime.now(clock);
        LocalDateTime deadline = pubDate.plus(2, ChronoUnit.HOURS);

        if (event.getEventDate().isBefore(deadline)) {
            throw new TooLittleTimeLeftBeforeEventStartException("too little time before event");
        }

        event.setState(EventState.PUBLISHED);
        event.setPublishedOn(pubDate);

        repository.save(event);

        return event;
    }

    @Override
    @Transactional
    public Event rejectEvent(long eventId) {
        Event event = repository.findById(eventId).orElseThrow(()
            -> new EventNotFoundException("not found"));

        if (event.getState() == EventState.PUBLISHED) {
            throw new EventIsNotAbleToChangeException("event is published");
        }

        event.setState(EventState.CANCELED);

        repository.save(event);

        return event;
    }

    @Override
    public List<Event> getEventsOfUser(long userId, int from, int size) {
        return repository.findEventsByInitiator_Id(userId, PageRequest.of(from, size));
    }

    @Override
    public Optional<Event> getUserEvent(long userId, long eventId) {
        Optional<Event> eventOptional = repository.findById(eventId);

        User user = userService.findUser(userId).orElseThrow(()
            -> new UserNotFoundException("not found"));

        if (eventOptional.isPresent() && !user.equals(eventOptional.get().getInitiator())) {
            throw new UserIsNotAnInitiatorOfEventException("user is not an initiator of event");
        }

        return eventOptional;
    }

    @Override
    public Optional<Event> getPublishedEvent(long eventId) {
        Optional<Event> eventOpt = repository.findByIdAndState(eventId, EventState.PUBLISHED);

        eventOpt.ifPresent(e -> {
            e.increaseViews();
            repository.save(e);
        });

        return eventOpt;
    }

    @Override
    public List<Event> getPublishedEvents(
        String text,
        List<Long> categories,
        Boolean paid,
        LocalDateTime rangeStart,
        LocalDateTime rangeEnd,
        Boolean onlyAvailable,
        String sort,
        int from,
        int size
    ) {
        return repository.searchForEvents(text, categories, paid, rangeStart, rangeEnd,
            onlyAvailable, sort, from, size);
    }

    @Override
    public Optional<Event> findEvent(long eventId) {
        return repository.findById(eventId);
    }

    @Override
    public List<Event> findEvents(Set<Long> ids) {
        return repository.findEventsByIdIn(ids);
    }

    @Override
    public List<Event> getEvents(List<Long> users, List<EventState> states, List<Long> categories,
        LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size) {
        return repository.searchForEvents(users, states, categories,
            rangeStart, rangeEnd, from, size);
    }

    @Override
    @Transactional
    public void updateCountOfConfirmedParticipants(long eventId, int count) {
        Event event = repository.findById(eventId).orElseThrow(()
            -> new EventNotFoundException("not found"));

        event.setConfirmedRequests(count);

        repository.save(event);
    }

    @Override
    public List<Event> findEventsOfCategory(long categoryId) {
        return repository.findEventsByCategory_Id(categoryId);
    }
}
