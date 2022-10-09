package ru.practicum.explorewithme.main.service.api.exception;

public class OnlyPendingParticipationRequestsCanBeCanceledException extends ConditionsAreNotMetException {

    public OnlyPendingParticipationRequestsCanBeCanceledException() {
    }

    public OnlyPendingParticipationRequestsCanBeCanceledException(String message) {
        super(message);
    }

    public OnlyPendingParticipationRequestsCanBeCanceledException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnlyPendingParticipationRequestsCanBeCanceledException(Throwable cause) {
        super(cause);
    }
}
