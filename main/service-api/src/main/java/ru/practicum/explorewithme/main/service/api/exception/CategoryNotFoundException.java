package ru.practicum.explorewithme.main.service.api.exception;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException() {

    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
