package com.diploma.skillmaster.dto;

import com.diploma.skillmaster.model.Step;
import com.diploma.skillmaster.model.UserEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
public class CoursesDto {
    private Long id;
    @Min(4)
    @Max(40)
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String name;
    @Min(10)
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String description;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private String imageUrl;
    private Instant createdOn;
    private Instant updatedOn;
    private UserEntity createdBy;
    private List<Step> steps;
}
