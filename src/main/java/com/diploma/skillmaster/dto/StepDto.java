package com.diploma.skillmaster.dto;

import com.diploma.skillmaster.model.Course;
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
    @Size(min = 10, max = 3000, message = "Опис кроку повинен бути від 10 до 3000 символів")
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String content;
    private String imageUrl;
    private String videoUrl;
    private Course course;

    @Override
    public int compareTo(StepDto o) {
        return this.id.compareTo(o.id);
    }
}
