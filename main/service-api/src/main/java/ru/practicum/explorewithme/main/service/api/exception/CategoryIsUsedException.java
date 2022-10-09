package ru.practicum.explorewithme.main.service.api.exception;

public class CategoryIsUsedException extends ConditionsAreNotMetException {

    public CategoryIsUsedException() {
    }

    public CategoryIsUsedException(String message) {
        super(message);
    }

    public CategoryIsUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryIsUsedException(Throwable cause) {
        super(cause);
    }
}
