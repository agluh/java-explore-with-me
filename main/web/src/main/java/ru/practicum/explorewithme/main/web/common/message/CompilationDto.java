package ru.practicum.explorewithme.main.web.common.message;

import java.util.List;
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
public class CompilationDto {

    private long id;

    private String title;

    private boolean pinned;

    private List<EventShortDto> events;
}
