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

import static com.diploma.skillmaster.mapper.StepMapper.mapToStep;
import static com.diploma.skillmaster.mapper.StepMapper.mapToStepDto;

@Service
@RequiredArgsConstructor
public class StepService {
    private final StepRepository stepRepository;
    private final CourseRepository courseRepository;

    public void createStep(Long courseId, StepDto stepDto) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        Step step = mapToStep(stepDto);
        step.setCourse(course);
        stepRepository.save(step);
    }

    public List<StepDto> findAllSteps() {
        List<Step> steps = stepRepository.findAll();
        return steps.stream().map(StepMapper::mapToStepDto).toList();
    }

    public StepDto findStepById(Long stepId) {
        Step step = stepRepository.findById(stepId).orElseThrow();
        return mapToStepDto(step);
    }

    public void updateStep(StepDto stepDto) {
        Step step = mapToStep(stepDto);
        stepRepository.save(step);
    }

    public void delete(Long stepId) {
        stepRepository.deleteById(stepId);
    }
}