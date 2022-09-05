package ru.practicum.explorewithme.main.service.api.exception;

public class UserIsNotAnInitiatorOfEventException extends ConditionsAreNotMetException {

    public UserIsNotAnInitiatorOfEventException() {
    }

    public UserIsNotAnInitiatorOfEventException(String message) {
        super(message);
    }

    public UserIsNotAnInitiatorOfEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsNotAnInitiatorOfEventException(Throwable cause) {
        super(cause);
    }
}
