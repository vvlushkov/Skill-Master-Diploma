package com.diploma.skillmaster.controller;

import com.diploma.skillmaster.dto.StepDto;
import com.diploma.skillmaster.mapper.CourseMapper;
import com.diploma.skillmaster.service.CourseService;
import com.diploma.skillmaster.service.StepService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling step-related requests.
 */
@Controller
@RequestMapping("/course/step")
@RequiredArgsConstructor
public class StepController {
    private final StepService stepService;
    private final CourseService courseService;

    @GetMapping("/new")
    public String saveStepForm(Model model,
                               @RequestParam(name = "courseId") Long courseId) {
        StepDto stepDto = new StepDto();
        model.addAttribute("step", stepDto);

        String mode = "NEW";
        model.addAttribute("mode", mode);
        model.addAttribute("courseId", courseId);

        return "step-save";
    }

    @GetMapping("/{stepId:\\d+}/edit")
    public String editStepForm(@PathVariable("stepId") Long stepId,
                               Model model,
                               @RequestParam(name = "courseId") Long courseId) {
        StepDto stepDto = stepService.findById(stepId);
        model.addAttribute("step", stepDto);

        String mode = "EDIT";
        model.addAttribute("mode", mode);
        model.addAttribute("courseId", courseId);

        return "step-save";
    }

    @PostMapping("/save")
    public String saveStep(@Valid @ModelAttribute("step") StepDto stepDto,
                           BindingResult result,
                           Model model,
                           @ModelAttribute("mode") String mode,
                           @RequestParam(name = "courseId") Long courseId,
                           @RequestParam(name = "stepId", required = false) Long stepId) {
        if (result.hasErrors()) {
            stepDto.setId(stepId);
            model.addAttribute("step", stepDto);
            model.addAttribute("mode", mode);
            model.addAttribute("courseId", courseId);
            return "step-save";
        }

        if ((mode != null) && (mode.equals("EDIT")) && (stepId != null)) {
            stepDto.setId(stepId);
        }
        stepDto.setCourse(CourseMapper.mapToCourse(courseService.findById(courseId)));
        stepService.save(stepDto);
        return "redirect:/course/" + courseId;
    }

    @GetMapping("{stepId:\\d+}/delete")
    public String deleteStep(@PathVariable("stepId") Long stepId,
                             @RequestParam(name = "courseId") Long courseId) {
        stepService.delete(stepId);
        return "redirect:/course/" + courseId;
    }
}