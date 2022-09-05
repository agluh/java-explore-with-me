package ru.practicum.explorewithmemain.participation;

import java.time.Instant;
import ru.practicum.explorewithmemain.event.Event;
import ru.practicum.explorewithmemain.user.User;

/**
 * Represents a request for an event participation.
 */
public class ParticipationRequest {

    private long id;

    private Event event;

    private User requester;

    private ParticipationStatus status;

    private Instant createdOn;
}
