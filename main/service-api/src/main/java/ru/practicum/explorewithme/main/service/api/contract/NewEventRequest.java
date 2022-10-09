package ru.practicum.explorewithme.main.service.api.contract;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.Location;

@Getter
@Builder(setterPrefix = "with")
public class NewEventRequest {

    private String title;

    private String annotation;

    private String description;

    private long category;

    private LocalDateTime eventDate;

    private Location location;

    private boolean paid;

    private int participantLimit;

    private boolean requestModeration;

    private long initiator;

    public Event toModel() {
        return Event.builder()
            .withTitle(title)
            .withAnnotation(annotation)
            .withDescription(description)
            .withEventDate(eventDate)
            .withLocation(location)
            .withPaid(paid)
            .withParticipantLimit(participantLimit)
            .withRequestModeration(requestModeration)
            .build();
    }
}
