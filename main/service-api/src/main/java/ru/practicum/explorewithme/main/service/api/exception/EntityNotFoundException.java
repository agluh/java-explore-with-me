package ru.practicum.explorewithme.main.service.api.exception;

public class EntityNotFoundException extends AppException {

    public EntityNotFoundException() {

    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
