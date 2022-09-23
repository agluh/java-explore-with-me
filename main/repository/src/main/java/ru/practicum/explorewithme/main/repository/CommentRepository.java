package ru.practicum.explorewithme.main.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.main.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByEvent_Id(long eventId, Pageable pageable);
}
