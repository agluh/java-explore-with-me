package ru.practicum.explorewithme.main.web.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.main.model.EventState;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.web.admin.message.AdminUpdateEventDto;
import ru.practicum.explorewithme.main.web.common.mapping.EventMapper;
import ru.practicum.explorewithme.main.web.common.message.EventFullDto;

@RestController("adminEventController")
@RequestMapping("/admin/events")
@AllArgsConstructor
public class EventController {

    private final EventService service;
    private final EventMapper mapper;

    @GetMapping
    public List<EventFullDto> getEvents(
        @RequestParam(name = "users", required = false) List<Long> users,
        @RequestParam(name = "states", required = false) List<EventState> states,
        @RequestParam(name = "categories", required = false) List<Long> categories,
        @RequestParam(name = "rangeStart", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
        @RequestParam(name = "rangeEnd", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime rangeEnd,
        @RequestParam(name = "from", defaultValue = "0") int from,
        @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return service.getEvents(
            users,
            states,
            categories,
            rangeStart,
            rangeEnd,
            from,
            size
        ).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public EventFullDto updateEvent(
        @PathVariable(name = "id") long eventId,
        @RequestBody @Valid AdminUpdateEventDto dto
    ) {
        return mapper.toDto(service.updateEvent(mapper.fromRequest(dto, eventId)));
    }

    @PatchMapping("/{id}/publish")
    public EventFullDto publishEvent(@PathVariable(name = "id") long eventId) {
        return mapper.toDto(service.publishEvent(eventId));
    }

    @PatchMapping("/{id}/reject")
    public EventFullDto rejectEvent(@PathVariable(name = "id") long eventId) {
        return mapper.toDto(service.rejectEvent(eventId));
    }
}
