package ru.practicum.explorewithme.main.web.admin.message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
public class NewCategoryDto {

    @NotBlank
    @Size(min = 1, max = 255)
    private String name;
}
