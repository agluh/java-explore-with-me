package ru.practicum.explorewithme.main.service.api.exception;

public class TooLittleTimeLeftBeforeEventStartException extends ConditionsAreNotMetException {

    public TooLittleTimeLeftBeforeEventStartException() {
    }

    public TooLittleTimeLeftBeforeEventStartException(String message) {
        super(message);
    }

    public TooLittleTimeLeftBeforeEventStartException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooLittleTimeLeftBeforeEventStartException(Throwable cause) {
        super(cause);
    }
}
