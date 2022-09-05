package ru.practicum.explorewithme.main.service.api.exception;

public class CompilationNotFoundException extends EntityNotFoundException {

    public CompilationNotFoundException() {
    }

    public CompilationNotFoundException(String message) {
        super(message);
    }

    public CompilationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompilationNotFoundException(Throwable cause) {
        super(cause);
    }
}
