package ru.practicum.explorewithme.main.service.api;

import ru.practicum.explorewithme.main.model.Comment;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.OnlyParticipantCanCommentException;
import ru.practicum.explorewithme.main.service.api.exception.OnlyPublishedEventsCanBeCommentedException;
import ru.practicum.explorewithme.main.service.api.exception.UserNotFoundException;

public interface CommentService {

    /**
     * Leave a comment.
     *
     * @throws OnlyParticipantCanCommentException in case user is not an initiator nor participant
     * @throws OnlyPublishedEventsCanBeCommentedException if event is not published
     * @throws UserNotFoundException in case user not found
     * @throws EventNotFoundException in case event not found
     */
    Comment commentEvent(long userId, long eventId, String message);
}
