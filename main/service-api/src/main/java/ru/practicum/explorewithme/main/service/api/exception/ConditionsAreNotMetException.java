package ru.practicum.explorewithme.main.service.api.exception;

public class ConditionsAreNotMetException extends AppException {

    public ConditionsAreNotMetException() {
    }

    public ConditionsAreNotMetException(String message) {
        super(message);
    }

    public ConditionsAreNotMetException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConditionsAreNotMetException(Throwable cause) {
        super(cause);
    }
}
