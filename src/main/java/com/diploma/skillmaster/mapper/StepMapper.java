package com.diploma.skillmaster.mapper;

import com.diploma.skillmaster.dto.StepDto;
import com.diploma.skillmaster.model.Step;

public interface StepMapper {
    static Step mapToStep(StepDto stepDto) {
        return Step.builder()
                .id(stepDto.getId())
                .name(stepDto.getName())
                .content(stepDto.getContent())
                .imageUrl(stepDto.getImageUrl())
                .videoUrl(stepDto.getVideoUrl())
                .course(CourseMapper.mapToCourse(stepDto.getCourse()))
                .build();
    }

    static StepDto mapToStepDto(Step step) {
        return StepDto.builder()
                .id(step.getId())
                .name(step.getName())
                .content(step.getContent())
                .imageUrl(step.getImageUrl())
                .videoUrl(step.getVideoUrl())
                .course(CourseMapper.mapToCourseDto(step.getCourse()))
                .build();
    }
}
