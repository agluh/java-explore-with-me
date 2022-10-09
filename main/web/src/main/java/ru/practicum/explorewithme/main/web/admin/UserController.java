package ru.practicum.explorewithme.main.web.admin;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.main.service.api.UserService;
import ru.practicum.explorewithme.main.web.admin.message.NewUserDto;
import ru.practicum.explorewithme.main.web.common.mapping.UserMapper;
import ru.practicum.explorewithme.main.web.common.message.UserDto;

@RestController("adminUserController")
@RequestMapping("/admin/users")
@Validated
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public List<UserDto> getUsers(
        @RequestParam(name = "ids", required = false) List<Long> ids,
        @RequestParam(name = "from", defaultValue = "0") int from,
        @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return service.findUsers(ids, from, size)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @PostMapping
    public UserDto registerUser(@RequestBody @Valid NewUserDto req) {
        return mapper.toDto(service.registerUser(req.getEmail(), req.getName()));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") long userId) {
        service.removeUser(userId);
    }
}
