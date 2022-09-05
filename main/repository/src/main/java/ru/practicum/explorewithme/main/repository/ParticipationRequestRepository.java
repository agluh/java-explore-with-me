package ru.practicum.explorewithme.main.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.explorewithme.main.model.ParticipationRequest;

public interface ParticipationRequestRepository extends CrudRepository<ParticipationRequest, Long> {

    List<ParticipationRequest> findAllByRequester_Id(long userId);

    List<ParticipationRequest> findAllByEvent_Id(long eventId);

    @Query(
        value = "SELECT * FROM requests r WHERE r.event_id = ?1 AND r.status = 'PENDING' ",
        nativeQuery = true
    )
    List<ParticipationRequest> findAllPendingParticipationRequestsOfEvent(long eventId);

    @Query(
        value = "SELECT * FROM requests r WHERE r.event_id = ?2 AND r.requester_id = ?1",
        nativeQuery = true
    )
    Optional<ParticipationRequest> getParticipationRequestOfUserInEvent(long userId, long eventId);
}
