package ru.practicum.explorewithme.main.service.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventState;
import ru.practicum.explorewithme.main.model.ParticipationRequest;
import ru.practicum.explorewithme.main.model.ParticipationStatus;
import ru.practicum.explorewithme.main.model.User;
import ru.practicum.explorewithme.main.repository.ParticipationRequestRepository;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.service.api.ParticipationService;
import ru.practicum.explorewithme.main.service.api.UserService;
import ru.practicum.explorewithme.main.service.api.exception.DuplicatedParticipationRequestsAreNotAllowedException;
import ru.practicum.explorewithme.main.service.api.exception.EventInitiatorCanNotBeParticipantException;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.OnlyPendingParticipationRequestsCanBeCanceledException;
import ru.practicum.explorewithme.main.service.api.exception.ParticipantLimitReachedForEventException;
import ru.practicum.explorewithme.main.service.api.exception.ParticipationAllowedOnlyInPublishedEventsException;
import ru.practicum.explorewithme.main.service.api.exception.ParticipationRequestNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRequestRepository repository;
    private final EventService eventService;
    private final UserService userService;
    private final Clock clock;

    @Lazy
    private final ParticipationServiceImpl self;

    @Override
    @Transactional
    public ParticipationRequest requestParticipation(long userId, long eventId) {
        Event event = eventService.findEvent(eventId)
            .orElseThrow(() -> new EventNotFoundException("not found"));

        User user = userService.findUser(userId)
            .orElseThrow(() -> new UserNotFoundException("not found"));

        if (repository.getParticipationRequestOfUserInEvent(userId, eventId).isPresent()) {
            throw new DuplicatedParticipationRequestsAreNotAllowedException(
                "user is a participant already");
        }

        if (event.getInitiator().equals(user)) {
            throw new EventInitiatorCanNotBeParticipantException("initiator can not participate");
        }

        if (event.getState() != EventState.PUBLISHED) {
            throw new ParticipationAllowedOnlyInPublishedEventsException(
                "participation allowed only in published events");
        }

        int currentCount = event.getConfirmedRequests();
        int limit = event.getParticipantLimit();

        if (limit != 0 && currentCount == limit) {
            throw new ParticipantLimitReachedForEventException("limit of participants reached");
        }

        ParticipationRequest request = ParticipationRequest.builder()
            .withEvent(event)
            .withRequester(user)
            .withStatus(ParticipationStatus.PENDING)
            .withCreatedOn(LocalDateTime.now(clock))
            .build();

        if (!event.isRequestModeration()) {
            request.setStatus(ParticipationStatus.CONFIRMED);
        }

        repository.save(request);

        return request;
    }

    @Override
    @Transactional
    public ParticipationRequest cancelParticipationRequest(long userId, long requestId) {
        ParticipationRequest request = repository.findById(requestId)
            .orElseThrow(() -> new ParticipationRequestNotFoundException("not found"));

        User user = userService.findUser(userId)
            .orElseThrow(() -> new UserNotFoundException("not found"));

        if (request.getStatus() != ParticipationStatus.PENDING) {
            throw new OnlyPendingParticipationRequestsCanBeCanceledException(
                "participation request is not in pending state");
        }

        request.setStatus(ParticipationStatus.CANCELED);

        repository.save(request);

        return request;
    }

    @Override
    @Transactional
    public ParticipationRequest confirmParticipationRequest(long userId, long eventId, long requestId) {
        ParticipationRequest request = repository.findById(requestId)
            .orElseThrow(() -> new ParticipationRequestNotFoundException("not found"));

        int currentCount = request.getEvent().getConfirmedRequests();
        int limit = request.getEvent().getParticipantLimit();

        if (limit != 0 && currentCount == limit) {
            throw new ParticipantLimitReachedForEventException("limit of participants reached");
        }

        if (currentCount == limit - 1) {
            self.rejectAllPendingParticipationRequestsOfEvent(request.getEvent().getId());
        }

        request.setStatus(ParticipationStatus.CONFIRMED);

        eventService.updateCountOfConfirmedParticipants(eventId, currentCount + 1);

        repository.save(request);

        return request;
    }

    @Override
    @Transactional
    public ParticipationRequest rejectParticipationRequest(long userId, long eventId, long requestId) {
        ParticipationRequest request = repository.findById(requestId)
            .orElseThrow(() -> new ParticipationRequestNotFoundException("not found"));

        request.setStatus(ParticipationStatus.REJECTED);

        repository.save(request);

        return request;
    }

    @Override
    public List<ParticipationRequest> getParticipationRequestsOfUser(long userId) {
        return repository.findAllByRequester_Id(userId);
    }

    @Override
    public List<ParticipationRequest> getParticipationRequestsOfEvent(long userId, long eventId) {
        return repository.findAllByEvent_Id(eventId);
    }

    @Override
    public void rejectAllPendingParticipationRequestsOfEvent(long eventId) {
        List<ParticipationRequest> pendingRequests =
            repository.findAllPendingParticipationRequestsOfEvent(eventId);

        for (ParticipationRequest r : pendingRequests) {
            r.setStatus(ParticipationStatus.REJECTED);
        }

        repository.saveAll(pendingRequests);
    }
}
