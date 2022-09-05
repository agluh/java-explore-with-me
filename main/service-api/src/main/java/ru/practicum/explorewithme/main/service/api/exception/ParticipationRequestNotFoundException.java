package ru.practicum.explorewithme.main.service.api.exception;

public class ParticipationRequestNotFoundException extends EntityNotFoundException {

    public ParticipationRequestNotFoundException() {
    }

    public ParticipationRequestNotFoundException(String message) {
        super(message);
    }

    public ParticipationRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipationRequestNotFoundException(Throwable cause) {
        super(cause);
    }
}
