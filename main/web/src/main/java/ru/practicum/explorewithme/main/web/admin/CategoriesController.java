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
import ru.practicum.explorewithme.main.service.api.CategoryService;
import ru.practicum.explorewithme.main.web.admin.message.NewCategoryDto;
import ru.practicum.explorewithme.main.web.admin.message.UpdateCategoryDto;
import ru.practicum.explorewithme.main.web.common.mapping.CategoryMapper;
import ru.practicum.explorewithme.main.web.common.message.CategoryDto;

@RestController("adminCategoriesController")
@RequestMapping("/admin/categories")
@AllArgsConstructor
public class CategoriesController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @PostMapping
    public CategoryDto addCategory(@RequestBody @Valid NewCategoryDto request) {
        return mapper.toDto(service.addNewCategory(request.getName()));
    }

    @PatchMapping
    public CategoryDto updateCategory(@RequestBody @Valid UpdateCategoryDto request) {
        return mapper.toDto(service.updateCategory(request.getId(), request.getName()));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(name = "id") long categoryId) {
        service.deleteCategory(categoryId);
    }
}
