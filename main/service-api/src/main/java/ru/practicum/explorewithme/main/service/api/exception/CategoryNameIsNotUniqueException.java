package ru.practicum.explorewithme.main.service.api.exception;

public class CategoryNameIsNotUniqueException extends ConflictException {

    public CategoryNameIsNotUniqueException() {
    }

    public CategoryNameIsNotUniqueException(String message) {
        super(message);
    }

    public CategoryNameIsNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNameIsNotUniqueException(Throwable cause) {
        super(cause);
    }
}
