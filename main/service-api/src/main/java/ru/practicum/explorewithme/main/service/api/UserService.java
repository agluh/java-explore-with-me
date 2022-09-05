package ru.practicum.explorewithme.main.service.api;

import java.util.List;
import java.util.Optional;
import ru.practicum.explorewithme.main.model.User;
import ru.practicum.explorewithme.main.service.api.exception.EmailIsNotUniqueException;
import ru.practicum.explorewithme.main.service.api.exception.UserNotFoundException;

public interface UserService {

    /**
     * Retrieves list of users by its ids.
     */
    List<User> findUsers(List<Long> ids, int start, int size);

    /**
     * Retrieves user by its id.
     */
    Optional<User> findUser(long userId);

    /**
     * Registers a new user.
     *
     * @throws EmailIsNotUniqueException in case user's email is not unique
     */
    User registerUser(String email, String name);

    /**
     * Removes a user.
     *
     * @throws UserNotFoundException in case user is not found
     */
    void removeUser(long userId);
}
