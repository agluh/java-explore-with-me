package ru.practicum.explorewithme.main.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventsCompilation;
import ru.practicum.explorewithme.main.repository.CompilationRepository;
import ru.practicum.explorewithme.main.service.api.CompilationService;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.service.api.exception.CompilationNotFoundException;
import ru.practicum.explorewithme.main.service.api.exception.EventNotFoundException;

@Service
@AllArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository repository;
    private final EventService eventService;

    @Override
    public EventsCompilation createCompilationOfEvents(String title, boolean isPinned,
        List<Long> events) {

        EventsCompilation compilation = new EventsCompilation(null, title, isPinned);

        for (long e : events) {
            eventService.findEvent(e).ifPresent(compilation::addEvent);
        }

        repository.save(compilation);

        return compilation;
    }

    @Override
    public void removeCompilation(long compilationId) {
        EventsCompilation compilation = repository.findById(compilationId)
            .orElseThrow(() -> new CompilationNotFoundException("not found"));

        repository.delete(compilation);
    }

    @Override
    public void addEventToCompilation(long eventId, long compilationId) {
        EventsCompilation compilation = repository.findById(compilationId)
            .orElseThrow(() -> new CompilationNotFoundException("not found"));

        Event event = eventService.findEvent(eventId)
            .orElseThrow(() -> new EventNotFoundException("not found"));

        compilation.addEvent(event);

        repository.save(compilation);
    }

    @Override
    public void removeEventFromCompilation(long eventId, long compilationId) {
        EventsCompilation compilation = repository.findById(compilationId)
            .orElseThrow(() -> new CompilationNotFoundException("not found"));

        Event event = eventService.findEvent(eventId)
            .orElseThrow(() -> new EventNotFoundException("not found"));

        compilation.removeEvent(event);

        repository.save(compilation);
    }

    @Override
    public void changePinnedStatus(long compilationId, boolean isPinned) {
        EventsCompilation compilation = repository.findById(compilationId)
            .orElseThrow(() -> new CompilationNotFoundException("not found"));

        compilation.setPinned(isPinned);

        repository.save(compilation);
    }

    @Override
    public List<EventsCompilation> findEventsCompilations(Boolean pinned, int from, int size) {
        if(pinned != null) {
            return repository.findAllByPinned(pinned, PageRequest.of(from, size));
        }

        return repository.findAll(PageRequest.of(from, size));
    }

    @Override
    public Optional<EventsCompilation> findEventsCompilation(long compilationId) {
        return repository.findById(compilationId);
    }
}
