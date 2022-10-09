package ru.practicum.explorewithme.main.service.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import ru.practicum.explorewithme.main.model.EventsCompilation;
import ru.practicum.explorewithme.main.service.api.exception.CompilationNotFoundException;

public interface CompilationService {

    /**
     * Creates a new event`s compilation.
     */
    EventsCompilation createCompilationOfEvents(String title, boolean isPinned, Set<Long> events);

    /**
     * Removes a compilation.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    void removeCompilation(long compilationId);

    /**
     * Adds event to compilation.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    void addEventToCompilation(long eventId, long compilationId);

    /**
     * Removes event from compilation.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    void removeEventFromCompilation(long eventId, long compilationId);

    /**
     * Change compilation pinned status.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    void changePinnedStatus(long compilationId, boolean isPinned);

    /**
     * Retrieves list of events compilations.
     */
    List<EventsCompilation> findEventsCompilations(Boolean pinned, int from, int size);

    /**
     * Retrieves an event's compilation.
     */
    Optional<EventsCompilation> findEventsCompilation(long compilationId);
}
