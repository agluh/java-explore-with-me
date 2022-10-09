package ru.practicum.explorewithme.main.service.api.contract;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.explorewithme.main.model.Location;

@Getter
@Builder(setterPrefix = "with")
public class AdminUpdateEventRequest {

    private Long eventId;

    private String title;

    private String annotation;

    private String description;

    private Long category;

    private LocalDateTime eventDate;

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;
}
