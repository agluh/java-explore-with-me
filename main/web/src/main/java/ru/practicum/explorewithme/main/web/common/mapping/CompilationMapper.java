package ru.practicum.explorewithme.main.web.common.mapping;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.EventsCompilation;
import ru.practicum.explorewithme.main.web.common.message.CompilationDto;

@Component
@RequiredArgsConstructor
public class CompilationMapper {

    private final EventMapper eventMapper;

    public CompilationDto toDto(EventsCompilation compilation) {
        return CompilationDto.builder()
            .withId(compilation.getId())
            .withTitle(compilation.getTitle())
            .withPinned(compilation.isPinned())
            .withEvents(compilation.getEvents().stream().map(eventMapper::toShortDto)
                .collect(Collectors.toList()))
            .build();
    }
}
