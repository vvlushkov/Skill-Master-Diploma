package com.diploma.skillmaster.mapper;

import com.diploma.skillmaster.dto.CourseDto;
import com.diploma.skillmaster.model.Course;

public interface CourseMapper {
    static Course mapToCourse(CourseDto courseDto) {
        return Course.builder()
                .id(courseDto.getId())
                .name(courseDto.getName())
                .description(courseDto.getDescription())
                .imageUrl(courseDto.getImageUrl())
                .createdOn(courseDto.getCreatedOn())
                .updatedOn(courseDto.getUpdatedOn())
                .createdBy(courseDto.getCreatedBy())
                .steps(courseDto.getSteps()
                        .stream().map(StepMapper::mapToStep).toList())
                .build();
    }

    static CourseDto mapToCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .imageUrl(course.getImageUrl())
                .createdOn(course.getCreatedOn())
                .updatedOn(course.getUpdatedOn())
                .createdBy(course.getCreatedBy())
                .steps(course.getSteps()
                        .stream().map(StepMapper::mapToStepDto).toList())
                .build();
    }
}