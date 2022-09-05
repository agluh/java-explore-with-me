package ru.practicum.explorewithme.main.service.api.contract;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
public class UpdateEventRequest {

    private Long eventId;

    private String title;

    private String annotation;

    private String description;

    private Long category;

    private LocalDateTime eventDate;

    private Boolean paid;

    private Integer participantLimit;

    private Long requester;
}
