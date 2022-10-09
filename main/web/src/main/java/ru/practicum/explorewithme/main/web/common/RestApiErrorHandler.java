package ru.practicum.explorewithme.main.web.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.practicum.explorewithme.main.service.api.exception.ConditionsAreNotMetException;
import ru.practicum.explorewithme.main.service.api.exception.ConflictException;
import ru.practicum.explorewithme.main.service.api.exception.EntityNotFoundException;
import ru.practicum.explorewithme.main.web.common.message.ApiError;

@RestControllerAdvice
public class RestApiErrorHandler {

    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class,
        MethodArgumentTypeMismatchException.class,
        MissingServletRequestParameterException.class,
        IllegalArgumentException.class
    })
    public ResponseEntity<ApiError> handleBadRequest(Exception ex, WebRequest request) {
        ApiError error = ApiError.fromException(ex, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundRequest(Exception ex, WebRequest request) {
        ApiError error = ApiError.fromException(ex, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(ConditionsAreNotMetException.class)
    public ResponseEntity<ApiError> handleConditionsAreNotMet(Exception ex, WebRequest request) {
        ApiError error = ApiError.fromException(ex, HttpStatus.FORBIDDEN);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflict(Exception ex, WebRequest request) {
        ApiError error = ApiError.fromException(ex, HttpStatus.CONFLICT);
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
