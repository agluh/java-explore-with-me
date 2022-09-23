package ru.practicum.explorewithme.main.web.common.mapping;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.web.common.message.EventFullDto;
import ru.practicum.explorewithme.main.web.common.message.EventShortDto;

@Component
@AllArgsConstructor
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
}
