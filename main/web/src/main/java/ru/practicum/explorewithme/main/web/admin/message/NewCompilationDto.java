package ru.practicum.explorewithme.main.web.admin.message;

import java.util.Set;
import javax.validation.constraints.NotBlank;
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

    private Set<Long> events;

    private boolean pinned;

    @NotBlank
    private String title;
}
