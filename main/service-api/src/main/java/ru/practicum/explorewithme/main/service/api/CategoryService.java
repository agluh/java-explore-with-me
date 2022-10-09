package ru.practicum.explorewithme.main.service.api;

import java.util.List;
import java.util.Optional;
import ru.practicum.explorewithme.main.model.EventCategory;
import ru.practicum.explorewithme.main.service.api.exception.CategoryIsUsedException;
import ru.practicum.explorewithme.main.service.api.exception.CategoryNameIsNotUniqueException;
import ru.practicum.explorewithme.main.service.api.exception.CategoryNotFoundException;

public interface CategoryService {

    /**
     * Creates a new category.
     *
     * @throws CategoryNameIsNotUniqueException if category name is not unique
     */
    EventCategory addNewCategory(String name);

    /**
     * Updates a category.
     *
     * @throws CategoryNameIsNotUniqueException if category name is not unique
     * @throws CategoryNotFoundException if category not found
     */
    EventCategory updateCategory(long categoryId, String name);

    /**
     * Removes a category.
     *
     * @throws CategoryIsUsedException if category is still used by any events
     * @throws CategoryNotFoundException if category not found
     */
    void deleteCategory(long categoryId);

    /**
     * Retrieves list of categories.
     */
    List<EventCategory> listCategories(int from, int size);

    /**
     * Retrieves category by identity.
     */
    Optional<EventCategory> findCategory(long categoryId);
}
