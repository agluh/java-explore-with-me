package ru.practicum.explorewithme.main.service.api.exception;

public class EmailIsNotUniqueException extends ConflictException {

    public EmailIsNotUniqueException() {
    }

    public EmailIsNotUniqueException(String message) {
        super(message);
    }

    public EmailIsNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailIsNotUniqueException(Throwable cause) {
        super(cause);
    }
}
