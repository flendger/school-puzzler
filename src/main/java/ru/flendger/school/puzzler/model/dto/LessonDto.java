package ru.flendger.school.puzzler.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonDto {
    private Long id;
    private String name;
    private String title;
    private List<HeaderDto> headers;
    private List<TaskRowDto> tasks;
}
