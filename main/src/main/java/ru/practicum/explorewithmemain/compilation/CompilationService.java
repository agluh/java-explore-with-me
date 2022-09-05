package ru.practicum.explorewithmemain.compilation;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CompilationService {

    /**
     * Creates a new event`s compilation.
     */
    public void createCompilationOfEvents(String title, boolean isPinned, List<Long> events) {

    }

    /**
     * Removes a compilation.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    public void removeCompilation(long compilationId) {

    }

    /**
     * Adds event to compilation.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    public void addEventToCompilation(long eventId, long compilationId) {

    }

    /**
     * Removes event from compilation.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    public void removeEventFromCompilation(long eventId, long compilationId) {

    }

    /**
     * Change compilation pinned status.
     *
     * @throws CompilationNotFoundException in case compilation not found
     */
    public void changePinnedStatus(long compilationId, boolean isPinned) {

    }
}
