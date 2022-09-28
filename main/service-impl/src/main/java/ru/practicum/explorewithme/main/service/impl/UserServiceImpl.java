package ru.practicum.explorewithme.main.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.main.model.User;
import ru.practicum.explorewithme.main.repository.UserRepository;
import ru.practicum.explorewithme.main.service.api.UserService;
import ru.practicum.explorewithme.main.service.api.exception.EmailIsNotUniqueException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> findUsers(List<Long> ids, int from, int size) {
        return repository.findUsersByIdIn(ids, PageRequest.of(from, size));
    }

    @Override
    public Optional<User> findUser(long userId) {
        return repository.findById(userId);
    }

    @Override
    @Transactional
    public User registerUser(String email, String name) {
        User user = new User(null, email, name);

        try {
            repository.save(user);
            return user;
        } catch (DataIntegrityViolationException ex) {
            throw new EmailIsNotUniqueException(ex);
        }
    }

    @Override
    @Transactional
    public void removeUser(long userId) {
        repository.deleteById(userId);
    }
}
