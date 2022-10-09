package ru.practicum.explorewithme.main.web.common.mapping;

import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.User;
import ru.practicum.explorewithme.main.web.common.message.UserDto;
import ru.practicum.explorewithme.main.web.common.message.UserShortDto;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getName()
        );
    }

    public UserShortDto toShortDto(User user) {
        return new UserShortDto(
            user.getId(),
            user.getName()
        );
    }
}
