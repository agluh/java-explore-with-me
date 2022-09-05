package ru.practicum.explorewithme.main.service.api.exception;

public class ParticipationAllowedOnlyInPublishedEventsException extends ConditionsAreNotMetException {

    public ParticipationAllowedOnlyInPublishedEventsException() {
    }

    public ParticipationAllowedOnlyInPublishedEventsException(String message) {
        super(message);
    }

    public ParticipationAllowedOnlyInPublishedEventsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipationAllowedOnlyInPublishedEventsException(Throwable cause) {
        super(cause);
    }
}
