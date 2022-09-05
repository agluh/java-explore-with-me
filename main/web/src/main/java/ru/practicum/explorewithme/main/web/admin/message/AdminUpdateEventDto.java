package ru.practicum.explorewithme.main.web.admin.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.explorewithme.main.model.Location;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AdminUpdateEventDto {

    private String title;

    private String annotation;

    private String description;

    private Long category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;
}
