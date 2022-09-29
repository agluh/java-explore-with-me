package ru.practicum.explorewithme.main.web.common.mapping;

import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.service.api.contract.AdminUpdateEventRequest;
import ru.practicum.explorewithme.main.service.api.contract.NewEventRequest;
import ru.practicum.explorewithme.main.service.api.contract.UpdateEventRequest;
import ru.practicum.explorewithme.main.web.admin.message.AdminUpdateEventDto;
import ru.practicum.explorewithme.main.web.common.message.EventFullDto;
import ru.practicum.explorewithme.main.web.common.message.EventShortDto;
import ru.practicum.explorewithme.main.web.pub.message.NewEventDto;
import ru.practicum.explorewithme.main.web.pub.message.UpdateEventDto;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    public EventFullDto toDto(Event event) {
        return EventFullDto.builder()
            .withId(event.getId())
            .withTitle(event.getTitle())
            .withAnnotation(event.getAnnotation())
            .withDescription(event.getDescription())
            .withCategory(categoryMapper.toDto(event.getCategory()))
            .withEventDate(event.getEventDate())
            .withInitiator(userMapper.toShortDto(event.getInitiator()))
            .withLocation(event.getLocation())
            .withParticipantLimit(event.getParticipantLimit())
            .withConfirmedRequests(event.getConfirmedRequests())
            .withRequestModeration(event.isRequestModeration())
            .withState(event.getState())
            .withPaid(event.isPaid())
            .withCreatedOn(event.getCreatedOn())
            .withPublishedOn(event.getPublishedOn())
            .withViews(event.getViews())
            .withComments(event.getComments().stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList()))
            .build();
    }

    public EventShortDto toShortDto(Event event) {
        return EventShortDto.builder()
            .withId(event.getId())
            .withTitle(event.getTitle())
            .withAnnotation(event.getAnnotation())
            .withCategory(categoryMapper.toDto(event.getCategory()))
            .withEventDate(event.getEventDate())
            .withPaid(event.isPaid())
            .withInitiator(userMapper.toShortDto(event.getInitiator()))
            .withViews(event.getViews())
            .withCommentsCount(event.getComments().size())
            .build();
    }

    public NewEventRequest fromRequest(NewEventDto dto, long userId) {
        return NewEventRequest.builder()
            .withTitle(dto.getTitle())
            .withAnnotation(dto.getAnnotation())
            .withDescription(dto.getDescription())
            .withCategory(dto.getCategory())
            .withEventDate(dto.getEventDate())
            .withLocation(dto.getLocation())
            .withPaid(dto.getPaid())
            .withRequestModeration(dto.getRequestModeration())
            .withParticipantLimit(dto.getParticipantLimit())
            .withInitiator(userId)
            .build();
    }

    public UpdateEventRequest fromRequest(UpdateEventDto dto, long userId) {
        return UpdateEventRequest.builder()
            .withEventId(dto.getEventId())
            .withTitle(dto.getTitle())
            .withAnnotation(dto.getAnnotation())
            .withDescription(dto.getDescription())
            .withCategory(dto.getCategory())
            .withEventDate(dto.getEventDate())
            .withPaid(dto.getPaid())
            .withParticipantLimit(dto.getParticipantLimit())
            .withRequester(userId)
            .build();
    }

    public AdminUpdateEventRequest fromRequest(AdminUpdateEventDto dto, long eventId) {
        return AdminUpdateEventRequest.builder()
            .withEventId(eventId)
            .withTitle(dto.getTitle())
            .withAnnotation(dto.getAnnotation())
            .withDescription(dto.getDescription())
            .withCategory(dto.getCategory())
            .withEventDate(dto.getEventDate())
            .withLocation(dto.getLocation())
            .withPaid(dto.getPaid())
            .withRequestModeration(dto.getRequestModeration())
            .withParticipantLimit(dto.getParticipantLimit())
            .build();
    }
}
