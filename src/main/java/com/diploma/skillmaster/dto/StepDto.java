package com.diploma.skillmaster.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepDto implements Comparable<StepDto> {
    private Long id;
    @Size(min = 4, max = 40, message = "Назва кроку повинна бути завдовшки від 4 до 40 символів")
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String name;
    @Min(value = 10, message = "Опис кроку повинен бути від 10 символів")
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String content;
    private String imageUrl;
    private String videoUrl;
    private CourseDto course;

    @Override
    public int compareTo(StepDto o) {
        return this.id.compareTo(o.id);
    }
}
