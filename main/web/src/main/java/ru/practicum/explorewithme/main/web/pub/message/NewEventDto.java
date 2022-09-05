package ru.practicum.explorewithme.main.web.pub.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import ru.practicum.explorewithme.main.model.Location;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class NewEventDto {

    @Length(min = 3, max = 120)
    private String title;

    @Length(min = 20, max = 2000)
    private String annotation;

    @Length(min = 20, max = 7000)
    private String description;

    @Positive
    private Long category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private Location location;

    private Boolean paid;

    @PositiveOrZero
    private Integer participantLimit;

    private Boolean requestModeration;
}
