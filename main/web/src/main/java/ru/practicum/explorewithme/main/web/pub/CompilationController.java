package ru.practicum.explorewithme.main.web.pub;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.main.service.api.CompilationService;
import ru.practicum.explorewithme.main.service.api.exception.CompilationNotFoundException;
import ru.practicum.explorewithme.main.web.common.mapping.CompilationMapper;
import ru.practicum.explorewithme.main.web.common.message.CompilationDto;

@RestController
@RequestMapping("/compilations")
@AllArgsConstructor
public class CompilationController {

    private final CompilationService service;
    private final CompilationMapper mapper;

    @GetMapping
    public List<CompilationDto> getEventsCompilations(
        @RequestParam(name = "pinned", required = false) Boolean pinned,
        @RequestParam(name = "from", defaultValue = "0") int from,
        @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return service.findEventsCompilations(pinned, from, size)
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public CompilationDto getEventsCompilation(@PathVariable(name = "id") long compilationId) {
        return mapper.toDto(service.findEventsCompilation(compilationId)
            .orElseThrow(() -> new CompilationNotFoundException("not found")));
    }
}
