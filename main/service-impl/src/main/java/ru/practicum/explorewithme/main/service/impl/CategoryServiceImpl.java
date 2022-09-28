package ru.practicum.explorewithme.main.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.main.model.EventCategory;
import ru.practicum.explorewithme.main.repository.CategoryRepository;
import ru.practicum.explorewithme.main.service.api.CategoryService;
import ru.practicum.explorewithme.main.service.api.EventService;
import ru.practicum.explorewithme.main.service.api.exception.CategoryIsUsedException;
import ru.practicum.explorewithme.main.service.api.exception.CategoryNameIsNotUniqueException;
import ru.practicum.explorewithme.main.service.api.exception.CategoryNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    @Lazy private final EventService eventService;

    @Override
    @Transactional
    public EventCategory addNewCategory(String name) {
        EventCategory category = new EventCategory(null, name);

        try {
            repository.save(category);
            return category;
        } catch (DuplicateKeyException ex) {
            throw new CategoryNameIsNotUniqueException(ex);
        }
    }

    @Override
    @Transactional
    public EventCategory updateCategory(long categoryId, String name) {
        EventCategory category = repository.findById(categoryId).orElseThrow(() ->
            new CategoryNotFoundException("not found"));

        category.setName(name);

        try {
            repository.save(category);
            return category;
        } catch (DataIntegrityViolationException ex) {
            throw new CategoryNameIsNotUniqueException(ex);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(long categoryId) {
        EventCategory category = repository.findById(categoryId).orElseThrow(() ->
            new CategoryNotFoundException("not found"));

        if (!eventService.findEventsOfCategory(categoryId).isEmpty()) {
            throw new CategoryIsUsedException("category is used");
        }

        repository.delete(category);
    }

    @Override
    public List<EventCategory> listCategories(int from, int size) {
        return repository.findAll(PageRequest.of(from, size));
    }

    @Override
    public Optional<EventCategory> findCategory(long categoryId) {
        return repository.findById(categoryId);
    }
}
