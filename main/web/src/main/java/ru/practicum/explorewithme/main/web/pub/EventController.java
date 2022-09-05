package ru.practicum.explorewithme.main.web.pub;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.service.api.StatisticsClient;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;
import ru.practicum.explorewithme.main.web.common.mapping.EventMapper;
import ru.practicum.explorewithme.main.web.common.message.EventFullDto;
import ru.practicum.explorewithme.main.web.common.message.EventShortDto;
import ru.practicum.explorewithme.main.web.pub.message.EventsSort;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper mapper;
    private final StatisticsClient statisticsClient;

    @GetMapping
    public List<EventShortDto> getEvents(
        @RequestParam(name = "text", required = false) String text,
        @RequestParam(name = "categories", required = false) List<Long> categories,
        @RequestParam(name = "paid", required = false) Boolean paid,
        @RequestParam(name = "rangeStart", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
        @RequestParam(name = "rangeEnd", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
        @RequestParam(name = "onlyAvailable", required = false) Boolean onlyAvailable,
        @RequestParam(name = "sort", defaultValue = "EVENT_DATE") EventsSort sort,
        @RequestParam(name = "from", defaultValue = "0") int from,
        @RequestParam(name = "size", defaultValue = "10") int size,
        HttpServletRequest request
    ) {
        statisticsClient.saveEndpointHit(request.getRequestURI(), request.getRemoteAddr());

        return eventService.getPublishedEvents(text, categories, paid, rangeStart, rangeEnd,
                onlyAvailable, sort.name(), from, size)
            .stream()
            .map(mapper::toShortDto)
            .toList();
    }

    @GetMapping("/{id}")
    public EventFullDto getEvent(
        @PathVariable(name = "id") long eventId,
        HttpServletRequest request
    ) {
        statisticsClient.saveEndpointHit(request.getRequestURI(), request.getRemoteAddr());

        return mapper.toDto(eventService.getPublishedEvent(eventId)
            .orElseThrow(() -> new EventNotFoundException("not found")));
    }
}
