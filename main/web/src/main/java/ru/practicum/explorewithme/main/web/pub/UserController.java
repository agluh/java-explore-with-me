package ru.practicum.explorewithme.main.web.pub;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.main.service.api.CommentService;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.service.api.ParticipationService;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;
import ru.practicum.explorewithme.main.web.common.mapping.EventMapper;
import ru.practicum.explorewithme.main.web.common.mapping.ParticipationRequestMapper;
import ru.practicum.explorewithme.main.web.common.message.EventFullDto;
import ru.practicum.explorewithme.main.web.common.message.EventShortDto;
import ru.practicum.explorewithme.main.web.common.message.ParticipationRequestDto;
import ru.practicum.explorewithme.main.web.pub.message.NewCommentDto;
import ru.practicum.explorewithme.main.web.pub.message.NewEventDto;
import ru.practicum.explorewithme.main.web.pub.message.UpdateEventDto;

@RestController
@RequestMapping("/users/{id}")
@AllArgsConstructor
@Validated
public class UserController {
    private final EventService eventService;
    private final ParticipationService participationService;
    private final CommentService commentService;
    private final EventMapper eventMapper;
    private final ParticipationRequestMapper requestMapper;

    @GetMapping("/events")
    public List<EventShortDto> getEvents(
        @PathVariable("id") long userId,
        @RequestParam(name = "from", defaultValue = "0") int from,
        @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return eventService.getEventsOfUser(userId, from, size)
            .stream()
            .map(eventMapper::toShortDto)
            .collect(Collectors.toList());
    }

    @PatchMapping("/events")
    public EventFullDto updateEvent(
        @PathVariable("id") long userId,
        @RequestBody @Valid UpdateEventDto dto
    ) {
        return eventMapper.toDto(eventService.updateEvent(eventMapper.fromRequest(dto, userId)));
    }

    @PostMapping("/events")
    public EventFullDto createEvent(
        @PathVariable("id") long userId,
        @RequestBody @Valid NewEventDto dto
    ) {
        return eventMapper.toDto(eventService.addEvent(eventMapper.fromRequest(dto, userId)));
    }

    @GetMapping("/events/{eventId}")
    public EventFullDto getEvent(
        @PathVariable("id") long userId,
        @PathVariable("eventId") long eventId
    ) {
        return eventMapper.toDto(eventService.getUserEvent(userId, eventId)
            .orElseThrow(() -> new EventNotFoundException("not found")));
    }

    @PatchMapping("/events/{eventId}")
    public EventFullDto deleteEvent(
        @PathVariable("id") long userId,
        @PathVariable("eventId") long eventId
    ) {
        return eventMapper.toDto(eventService.cancelEvent(userId, eventId));
    }

    @GetMapping("/events/{eventId}/requests")
    public List<ParticipationRequestDto> getParticipationRequestOfEvent(
        @PathVariable("id") long userId,
        @PathVariable("eventId") long eventId
    ) {
        return participationService.getParticipationRequestsOfEvent(userId, eventId)
            .stream()
            .map(requestMapper::toDto)
            .collect(Collectors.toList());
    }

    @PatchMapping("/events/{eventId}/requests/{requestId}/confirm")
    public ParticipationRequestDto confirmParticipationRequest(
        @PathVariable("id") long userId,
        @PathVariable("eventId") long eventId,
        @PathVariable("requestId") long requestId
    ) {
        return requestMapper.toDto(
            participationService.confirmParticipationRequest(userId, eventId, requestId));
    }

    @PatchMapping("/events/{eventId}/requests/{requestId}/reject")
    public ParticipationRequestDto rejectParticipationRequest(
        @PathVariable("id") long userId,
        @PathVariable("eventId") long eventId,
        @PathVariable("requestId") long requestId
    ) {
        return requestMapper.toDto(
            participationService.rejectParticipationRequest(userId, eventId, requestId));
    }

    @GetMapping("/requests")
    public List<ParticipationRequestDto> getParticipationRequests(@PathVariable("id") long userId) {
        return participationService.getParticipationRequestsOfUser(userId)
            .stream()
            .map(requestMapper::toDto)
            .collect(Collectors.toList());
    }

    @PostMapping("/requests")
    public ParticipationRequestDto addParticipationRequest(
        @PathVariable("id") long userId,
        @RequestParam("eventId") long eventId
    ) {
        return requestMapper.toDto(participationService.requestParticipation(userId, eventId));
    }

    @PatchMapping("/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelParticipationRequest(
        @PathVariable("id") long userId,
        @PathVariable("requestId") long requestId
    ) {
        return requestMapper.toDto(
            participationService.cancelParticipationRequest(userId, requestId));
    }

    @PostMapping("/events/{eventId}/comments")
    public void addComment(
        @PathVariable("id") long userId,
        @PathVariable("eventId") long eventId,
        @RequestBody @Valid NewCommentDto request
    ) {
        commentService.commentEvent(userId, eventId, request.getComment());
    }
}
