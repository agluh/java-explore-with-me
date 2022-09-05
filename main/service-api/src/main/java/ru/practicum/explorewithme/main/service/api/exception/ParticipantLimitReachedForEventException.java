package ru.practicum.explorewithme.main.service.api.exception;

public class ParticipantLimitReachedForEventException extends ConditionsAreNotMetException {

    public ParticipantLimitReachedForEventException() {
    }

    public ParticipantLimitReachedForEventException(String message) {
        super(message);
    }

    public ParticipantLimitReachedForEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipantLimitReachedForEventException(Throwable cause) {
        super(cause);
    }
}
