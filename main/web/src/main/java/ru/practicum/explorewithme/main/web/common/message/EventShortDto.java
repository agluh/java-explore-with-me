package ru.practicum.explorewithme.main.web.common.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder(setterPrefix = "with")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EventShortDto {

    private long id;

    private String title;

    private String annotation;

    private CategoryDto category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private UserShortDto initiator;

    private boolean paid;

    private long views;
}
