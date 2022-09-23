package ru.practicum.explorewithme.main.service.api.exception;

public class OnlyParticipantCanCommentException extends ConditionsAreNotMetException {

    public OnlyParticipantCanCommentException() {
    }

    public OnlyParticipantCanCommentException(String message) {
        super(message);
    }

    public OnlyParticipantCanCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnlyParticipantCanCommentException(Throwable cause) {
        super(cause);
    }
}
