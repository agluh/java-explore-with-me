package ru.practicum.explorewithme.main.web.common.mapping;

import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.EventCategory;
import ru.practicum.explorewithme.main.web.common.message.CategoryDto;

@Component
public class CategoryMapper {

    public CategoryDto toDto(EventCategory category) {
        return new CategoryDto(
            category.getId(),
            category.getName()
        );
    }
}
