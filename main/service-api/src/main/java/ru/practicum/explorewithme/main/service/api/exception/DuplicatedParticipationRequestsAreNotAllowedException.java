package ru.practicum.explorewithme.main.service.api.exception;

public class DuplicatedParticipationRequestsAreNotAllowedException extends ConflictException {

    public DuplicatedParticipationRequestsAreNotAllowedException() {
    }

    public DuplicatedParticipationRequestsAreNotAllowedException(String message) {
        super(message);
    }

    public DuplicatedParticipationRequestsAreNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedParticipationRequestsAreNotAllowedException(Throwable cause) {
        super(cause);
    }
}
