package com.diploma.skillmaster.service;

import com.diploma.skillmaster.dto.StepDto;
import com.diploma.skillmaster.mapper.StepMapper;
import com.diploma.skillmaster.model.Course;
import com.diploma.skillmaster.model.Step;
import com.diploma.skillmaster.repository.CourseRepository;
import com.diploma.skillmaster.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import static com.diploma.skillmaster.mapper.StepMapper.mapToStep;
import static com.diploma.skillmaster.mapper.StepMapper.mapToStepDto;

/**
 * Service class for managing course steps.
 */
@Service
@RequiredArgsConstructor
public class StepService {
    private final StepRepository stepRepository;
    private final CourseRepository courseRepository;

    /**
     * Creates a step for a course.
     *
     * @param courseId The ID of the course to create the step for.
     * @param stepDto  The StepDto object containing step details.
     */
    public void createStep(Long courseId, StepDto stepDto) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        Step step = mapToStep(stepDto);
        step.setCourse(course);
        stepRepository.save(step);
    }

    /**
     * Retrieves all steps.
     *
     * @return A list of StepDto objects representing the steps.
     */
    public List<StepDto> findAllSteps() {
        List<Step> steps = stepRepository.findAll();
        return steps.stream().map(StepMapper::mapToStepDto).toList();
    }

    /**
     * Retrieves a step by its ID.
     *
     * @param stepId The ID of the step to retrieve.
     * @return The StepDto object representing the retrieved step.
     * @throws NoSuchElementException if the step is not found.
     */
    public StepDto findById(Long stepId) {
        Step step = stepRepository.findById(stepId).orElseThrow(() -> new NoSuchElementException("Step not found"));
        return mapToStepDto(step);
    }

    /**
     * Updates a step.
     *
     * @param stepDto The StepDto object containing step details to be updated.
     */
    public void save(StepDto stepDto) {
        Step step = mapToStep(stepDto);
        stepRepository.save(step);
    }

    /**
     * Deletes a step by its ID.
     *
     * @param stepId The ID of the step to delete.
     */
    public void delete(Long stepId) {
        stepRepository.deleteById(stepId);
    }
}