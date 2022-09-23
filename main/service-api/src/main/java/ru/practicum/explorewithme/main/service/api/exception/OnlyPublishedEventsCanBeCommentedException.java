package ru.practicum.explorewithme.main.service.api.exception;

public class OnlyPublishedEventsCanBeCommentedException extends ConditionsAreNotMetException {

    public OnlyPublishedEventsCanBeCommentedException() {
    }

    public OnlyPublishedEventsCanBeCommentedException(String message) {
        super(message);
    }

    public OnlyPublishedEventsCanBeCommentedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnlyPublishedEventsCanBeCommentedException(Throwable cause) {
        super(cause);
    }
}
