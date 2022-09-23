package ru.practicum.explorewithme.main.web.pub.message;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
public class NewCommentDto {

    @NotBlank
    @Length(max = 1000, min = 1)
    private String comment;
}
