package ru.practicum.explorewithmemain.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    /**
     * Creates a new category.
     *
     * @throws CategoryNameIsNotUniqueException if category name is not unique
     */
    public void addNewCategory(String name) {

    }

    /**
     * Updates a category.
     *
     * @throws CategoryNameIsNotUniqueException if category name is not unique
     * @throws CategoryNotFoundException if category not found
     */
    public void updateCategory(long categoryId, String name) {

    }

    /**
     * Removes a category.
     *
     * @throws CategoryIsUsedException if category is still used by any events
     * @throws CategoryNotFoundException if category not found
     */
    public void deleteCategory(long categoryId) {

    }
}
