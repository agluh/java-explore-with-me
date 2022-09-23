package ru.practicum.explorewithme.main.web.common.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.explorewithme.main.model.EventState;
import ru.practicum.explorewithme.main.model.Location;

@Builder(setterPrefix = "with")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EventFullDto {

    private long id;

    private String title;

    private String annotation;

    private String description;

    private CategoryDto category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private UserShortDto initiator;

    private Location location;

    private int participantLimit;

    private int confirmedRequests;

    private boolean requestModeration;

    private EventState state;

    private boolean paid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;

    private long views;

    private List<CommentDto> comments;
}
