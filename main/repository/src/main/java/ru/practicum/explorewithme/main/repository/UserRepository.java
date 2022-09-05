package ru.practicum.explorewithme.main.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.main.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findUsersByIdIn(List<Long> ids, Pageable pageable);
}
