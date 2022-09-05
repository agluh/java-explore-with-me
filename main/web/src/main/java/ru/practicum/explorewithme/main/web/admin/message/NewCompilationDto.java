package ru.practicum.explorewithme.main.web.admin.message;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class NewCompilationDto {

    private List<Long> events;

    private boolean pinned;

    private String title;
}
