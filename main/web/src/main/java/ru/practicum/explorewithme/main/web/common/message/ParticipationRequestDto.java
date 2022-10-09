package ru.practicum.explorewithme.main.web.common.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.explorewithme.main.model.ParticipationStatus;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ParticipationRequestDto {

    private long id;

    private long event;

    private long requester;

    private ParticipationStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
}
