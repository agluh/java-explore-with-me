package ru.practicum.explorewithme.main.web.pub;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.main.service.api.CategoryService;
import ru.practicum.explorewithme.main.service.api.exception.CategoryNotFoundException;
import ru.practicum.explorewithme.main.web.common.mapping.CategoryMapper;
import ru.practicum.explorewithme.main.web.common.message.CategoryDto;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoriesController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping
    public List<CategoryDto> getCategories(
        @RequestParam(name = "from", defaultValue = "0") int from,
        @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return service.listCategories(from, size)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable(name = "id") long categoryId) {
        return mapper.toDto(service.findCategory(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException("not found")));
    }
}
