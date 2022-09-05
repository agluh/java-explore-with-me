package ru.practicum.explorewithme.main.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.main.model.EventsCompilation;

public interface CompilationRepository extends CrudRepository<EventsCompilation, Long> {

    List<EventsCompilation> findAll(Pageable pageable);

    List<EventsCompilation> findAllByPinned(boolean isPinned, Pageable pageable);
}
