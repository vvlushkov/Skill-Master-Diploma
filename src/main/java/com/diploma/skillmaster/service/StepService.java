package com.diploma.skillmaster.service;

import com.diploma.skillmaster.dto.StepDto;
import com.diploma.skillmaster.mapper.CourseMapper;
import com.diploma.skillmaster.model.Step;
import com.diploma.skillmaster.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    private final CourseService courseService;

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
    public void save(Long courseId, StepDto stepDto) {
        stepDto.setCourse(CourseMapper.mapToCourse(courseService.findById(courseId)));
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