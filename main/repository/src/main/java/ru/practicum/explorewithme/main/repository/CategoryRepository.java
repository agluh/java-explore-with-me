package ru.practicum.explorewithme.main.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.main.model.EventCategory;

public interface CategoryRepository extends CrudRepository<EventCategory, Long> {

    List<EventCategory> findAll(Pageable pageable);
}
