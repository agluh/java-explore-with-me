package ru.practicum.explorewithme.main.service.api;

import java.util.List;
import ru.practicum.explorewithme.main.model.ParticipationRequest;
import ru.practicum.explorewithme.main.model.ParticipationStatus;

public interface ParticipationService {

    /**
     * Requests a participation in event.
     */
    ParticipationRequest requestParticipation(long userId, long eventId);

    /**
     * Cancels self participation request of a user.
     */
    ParticipationRequest cancelParticipationRequest(long userId, long requestId);

    /**
     * Confirms a participation request from the user.
     */
    ParticipationRequest confirmParticipationRequest(long userId, long eventId, long requestId);

    /**
     * Rejects a participation request from the user.
     */
    ParticipationRequest rejectParticipationRequest(long userId, long eventId, long requestId);

    /**
     * Retrieves list of users participation requests.
     */
    List<ParticipationRequest> getParticipationRequestsOfUser(long userId);

    /**
     * Retrieves list of participation requests for event.
     */
    List<ParticipationRequest> getParticipationRequestsOfEvent(long userId, long eventId);

    /**
     * Rejects all pending participation requests of event.
     */
    void rejectAllPendingParticipationRequestsOfEvent(long eventId);

    /**
     * Retrieves list of participation requests with status.
     */
    List<ParticipationRequest> getParticipationRequestsOfEventWithStatus(long eventId,
        ParticipationStatus status);
}
