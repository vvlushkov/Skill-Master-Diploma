package com.diploma.skillmaster.dto;

import com.diploma.skillmaster.model.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto implements Comparable<CourseDto>{
    private Long id;
    @Size(min = 4, max = 40, message = "Назва курсу повинна бути завдовшки від 4 до 40 символів")
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String name;
    @Size(min = 10, max = 3000, message = "Опис курсу повинен бути від 10 до 3000 символів")
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String description;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String imageUrl;
    private Instant createdOn;
    private Instant updatedOn;
    private UserEntity creator;
    private List<StepDto> steps;

    @Override
    public int compareTo(CourseDto o) {
        return this.id.compareTo(o.id);
    }
}
