package ru.practicum.explorewithme.main.service.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.main.model.Comment;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventState;
import ru.practicum.explorewithme.main.model.ParticipationRequest;
import ru.practicum.explorewithme.main.model.ParticipationStatus;
import ru.practicum.explorewithme.main.model.User;
import ru.practicum.explorewithme.main.service.api.CommentService;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.service.api.ParticipationService;
import ru.practicum.explorewithme.main.service.api.UserService;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.OnlyParticipantCanCommentException;
import ru.practicum.explorewithme.main.service.api.exception.OnlyPublishedEventsCanBeCommentedException;
import ru.practicum.explorewithme.main.service.api.exception.UserNotFoundException;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final EventService eventService;
    private final ParticipationService participationService;
    private final Clock clock;

    @Override
    public Comment commentEvent(long userId, long eventId, String message) {
        User user = userService.findUser(userId).orElseThrow(
            () -> new UserNotFoundException("not found"));

        Event event = eventService.findEvent(eventId).orElseThrow(
            () -> new EventNotFoundException("not found"));

        if (event.getState() != EventState.PUBLISHED) {
            throw new OnlyPublishedEventsCanBeCommentedException("event is not published");
        }

        Set<User> participants = participationService.getParticipationRequestsOfEventWithStatus(eventId,
            ParticipationStatus.CONFIRMED)
            .stream()
            .map(ParticipationRequest::getRequester)
            .collect(Collectors.toSet());

        if (!event.getInitiator().equals(user) && !participants.contains(user)) {
            throw new OnlyParticipantCanCommentException(
                "only initiator or participant can comment");
        }

        Comment comment = Comment.builder()
            .withMessage(message)
            .withAuthor(user)
            .withEvent(event)
            .withCreatedAt(LocalDateTime.now(clock))
            .build();

        eventService.addComment(eventId, comment);

        return comment;
    }
}
