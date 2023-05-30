package com.diploma.skillmaster.dto;

import com.diploma.skillmaster.model.Course;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepDto {
    private Long id;
    @Min(value = 4, message = "Максимальна довжина = 4 символа")
    @Max(value = 40, message = "Максимальна довжина = 40 символів")
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String name;
    @Min(value = 10, message = "Максимальна довжина = 10 символів")
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String content;
    private String imageUrl;
    private String videoUrl;
    private Course course;
}
