package ru.practicum.explorewithme.main.service.api.exception;

public class EventInitiatorCanNotBeParticipantException extends ConditionsAreNotMetException {

    public EventInitiatorCanNotBeParticipantException() {
    }

    public EventInitiatorCanNotBeParticipantException(String message) {
        super(message);
    }

    public EventInitiatorCanNotBeParticipantException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventInitiatorCanNotBeParticipantException(Throwable cause) {
        super(cause);
    }
}
