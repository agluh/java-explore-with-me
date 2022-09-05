package ru.practicum.explorewithmemain.event;

import java.time.Instant;
import java.time.ZonedDateTime;
import ru.practicum.explorewithmemain.category.EventCategory;
import ru.practicum.explorewithmemain.user.User;

/**
 * Represents an event in a time.
 */
public class Event {

    private String title;

    private String annotation;

    private String description;

    private EventCategory category;

    private ZonedDateTime eventDate;

    private Location location;

    private boolean paid;

    private int participantLimit;

    private boolean requestModeration;

    private User initiator;

    private EventState state;

    private Instant createdOn;

    private Instant publishedOn;
}
