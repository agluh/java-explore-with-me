package ru.practicum.explorewithme.main.web.admin.message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class UpdateCategoryDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;
}
