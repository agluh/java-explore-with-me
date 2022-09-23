package ru.practicum.explorewithme.main.web.common.message;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiError {

    private List<String> errors;

    private String message;

    private String reason;

    private HttpStatus status;

    private LocalDateTime timestamp;

    public static ApiError fromException(Throwable e, HttpStatus status) {
        String reason = "";
        return new ApiError(
            Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::toString)
                    .collect(Collectors.toList()),
            e.getMessage(),
            reason,
            status,
            LocalDateTime.now());
    }
}
