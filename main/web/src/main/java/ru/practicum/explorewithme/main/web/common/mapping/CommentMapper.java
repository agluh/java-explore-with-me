package ru.practicum.explorewithme.main.web.common.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.main.model.Comment;
import ru.practicum.explorewithme.main.web.common.message.CommentDto;

@Component
@AllArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;

    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
            .withMessage(comment.getMessage())
            .withAuthor(userMapper.toShortDto(comment.getAuthor()))
            .withCreatedAt(comment.getCreatedAt())
            .build();
    }
}
