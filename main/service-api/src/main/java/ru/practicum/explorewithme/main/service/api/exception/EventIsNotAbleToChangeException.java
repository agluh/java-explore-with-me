package ru.practicum.explorewithme.main.service.api.exception;

public class EventIsNotAbleToChangeException extends ConditionsAreNotMetException {

    public EventIsNotAbleToChangeException() {
    }

    public EventIsNotAbleToChangeException(String message) {
        super(message);
    }

    public EventIsNotAbleToChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventIsNotAbleToChangeException(Throwable cause) {
        super(cause);
    }
}
