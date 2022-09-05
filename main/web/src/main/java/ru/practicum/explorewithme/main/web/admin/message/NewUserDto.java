package ru.practicum.explorewithme.main.web.admin.message;

import javax.validation.constraints.Email;
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
public class NewUserDto {

    @Email
    private String email;

    @NotBlank
    private String name;
}
