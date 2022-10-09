package ru.practicum.explorewithme.main.service.api.exception;

public class EventNotFoundException extends EntityNotFoundException {

    public EventNotFoundException() {
    }

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventNotFoundException(Throwable cause) {
        super(cause);
    }
}
