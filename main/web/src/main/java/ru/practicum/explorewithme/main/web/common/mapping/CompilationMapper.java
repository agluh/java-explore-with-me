package ru.practicum.explorewithme.main.web.common.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventsCompilation;
import ru.practicum.explorewithme.main.web.common.message.CompilationDto;

@Component
@AllArgsConstructor
public class CompilationMapper {

    private final EventMapper eventMapper;

    public CompilationDto toDto(EventsCompilation compilation) {
        return CompilationDto.builder()
            .withId(compilation.getId())
            .withTitle(compilation.getTitle())
            .withPinned(compilation.isPinned())
            .withEvents(compilation.getEvents().stream().map(eventMapper::toShortDto).toList())
            .build();
    }
}
