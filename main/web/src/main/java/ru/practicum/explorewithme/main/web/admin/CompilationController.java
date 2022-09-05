package ru.practicum.explorewithme.main.web.admin;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.main.service.api.CompilationService;
import ru.practicum.explorewithme.main.web.admin.message.NewCompilationDto;
import ru.practicum.explorewithme.main.web.common.mapping.CompilationMapper;
import ru.practicum.explorewithme.main.web.common.message.CompilationDto;

@RestController("adminCompilationController")
@RequestMapping("/admin/compilations")
@AllArgsConstructor
public class CompilationController {

    private final CompilationService service;
    private final CompilationMapper mapper;

    @PostMapping
    public CompilationDto addCompilation(@RequestBody @Valid NewCompilationDto req) {
        return mapper.toDto(service.createCompilationOfEvents(
            req.getTitle(),
            req.isPinned(),
            req.getEvents()
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteCompilation(@PathVariable(name = "id") long compilationId) {
        service.removeCompilation(compilationId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(
        @PathVariable(name = "compId") long compilationId,
        @PathVariable(name = "eventId") long eventId
    ) {
        service.addEventToCompilation(eventId, compilationId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(
        @PathVariable(name = "compId") long compilationId,
        @PathVariable(name = "eventId") long eventId
    ) {
        service.removeEventFromCompilation(eventId, compilationId);
    }

    @PatchMapping("/{id}/pin")
    public void pinCompilation(@PathVariable(name = "id") long compilationId) {
        service.changePinnedStatus(compilationId, true);
    }

    @DeleteMapping("/{id}/pin")
    public void unpinCompilation(@PathVariable(name = "id") long compilationId) {
        service.changePinnedStatus(compilationId, false);
    }
}
