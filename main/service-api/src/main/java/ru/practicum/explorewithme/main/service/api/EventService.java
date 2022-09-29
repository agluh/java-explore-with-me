package ru.practicum.explorewithme.main.service.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import ru.practicum.explorewithme.main.model.Comment;
import java.util.Set;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventState;
import ru.practicum.explorewithme.main.service.api.contract.AdminUpdateEventRequest;
import ru.practicum.explorewithme.main.service.api.contract.NewEventRequest;
import ru.practicum.explorewithme.main.service.api.contract.UpdateEventRequest;
import ru.practicum.explorewithme.main.service.api.exception.EventIsNotAbleToChangeException;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.TooLittleTimeLeftBeforeEventStartException;
import ru.practicum.explorewithme.main.service.api.exception.UserIsNotAnInitiatorOfEventException;

public interface EventService {

    /**
     * Adds a new event.
     *
     * @throws TooLittleTimeLeftBeforeEventStartException if less than 2 hours left before an event
     */
    Event addEvent(NewEventRequest request);

    /**
     * Updates an event.
     *
     * @throws TooLittleTimeLeftBeforeEventStartException if less than 2 hours left before an event
     * @throws EventIsNotAbleToChangeException            if event is not canceled or not await of
     *                                                    moderation
     * @throws EventNotFoundException                     in case event not found
     */
    Event updateEvent(UpdateEventRequest request);

    /**
     * Updates an event without validation.
     *
     * @throws EventNotFoundException                     in case event not found
     */
    Event updateEvent(AdminUpdateEventRequest request);

    /**
     * Cancel an event by initiator.
     *
     * @throws EventIsNotAbleToChangeException if event is not await of moderation
     * @throws EventNotFoundException          in case event not found
     */
    Event cancelEvent(long userId, long eventId);

    /**
     * Publishes an event.
     *
     * @throws EventNotFoundException in case event not found
     * @throws EventIsNotAbleToChangeException if event is already published
     */
    Event publishEvent(long eventId);

    /**
     * Rejects event publication.
     *
     * @throws EventNotFoundException in case event not found
     * @throws EventIsNotAbleToChangeException if event is already published
     */
    Event rejectEvent(long eventId);

    /**
     * Retrieves list of user's events.
     */
    List<Event> getEventsOfUser(long userId, int from, int size);

    /**
     * Retrieves event of a user by identity.
     *
     * @throws UserIsNotAnInitiatorOfEventException in case if user not initiated an event
     */
    Optional<Event> getUserEvent(long userId, long eventId);

    /**
     * Retrieves published event.
     */
    Optional<Event> getPublishedEvent(long eventId);

    /**
     * Retrieves published events.
     */
    List<Event> getPublishedEvents(
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

    /**
     * Retrieves event.
     */
    Optional<Event> findEvent(long eventId);

    /**
     * Retrieves list of events.
     */
    List<Event> findEvents(Set<Long> ids);

    /**
     * Retrieves events.
     */
    List<Event> getEvents(
        List<Long> users,
        List<EventState> states,
        List<Long> categories,
        LocalDateTime rangeStart,
        LocalDateTime rangeEnd,
        int from,
        int size
    );

    /**
     * Updates count of confirmed participants.
     */
    void updateCountOfConfirmedParticipants(long eventId, int count);

    List<Event> findEventsOfCategory(long categoryId);

    /**
     * Adds a comment to event.
     */
    void addComment(long eventId, Comment comment);
}
