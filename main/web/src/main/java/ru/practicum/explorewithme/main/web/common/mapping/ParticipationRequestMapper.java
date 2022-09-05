package ru.practicum.explorewithme.main.web.common.mapping;

import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.ParticipationRequest;
import ru.practicum.explorewithme.main.web.common.message.ParticipationRequestDto;

@Component
public class ParticipationRequestMapper {

    public ParticipationRequestDto toDto(ParticipationRequest request) {
        return new ParticipationRequestDto(
            request.getId(),
            request.getEvent().getId(),
            request.getRequester().getId(),
            request.getStatus(),
            request.getCreatedOn()
        );
    }
}
