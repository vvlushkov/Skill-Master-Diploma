package com.diploma.skillmaster.controller;

import com.diploma.skillmaster.dto.CourseDto;
import com.diploma.skillmaster.dto.StepDto;
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
@RequestMapping("/step")
@RequiredArgsConstructor
public class StepController {
    private final StepService stepService;

    @GetMapping("/new")
    public String saveStepForm(Model model) {
        StepDto stepDto = new StepDto();
        model.addAttribute("step", stepDto);
        model.addAttribute("courseId", stepDto.getId()); //WTF
        return "step-save";
    }

    @GetMapping("/{stepId:\\d+}/edit")
    public String editStepForm(@PathVariable("stepId") Long stepId, Model model) {
        StepDto stepDto = stepService.findById(stepId);
        model.addAttribute("step", stepDto);
        model.addAttribute("stepId", stepId); //WTF
        return "step-save";
    }

    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("step") StepDto stepDto,
                             @ModelAttribute("stepId") Long stepId,
                             @RequestParam("mode") String mode,
                             @ModelAttribute("course") CourseDto courseDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("step", stepDto);
            return "step-save";
        }

        if (mode.equals("EDIT")) {
            stepDto.setId(stepId);
        }

        stepDto.setCourse(courseDto);
        stepService.save(stepDto);
        return "redirect:/course/" + courseDto.getId();
    }

    @GetMapping("{stepId:\\d+}/delete")
    public String deleteStep(@PathVariable("stepId") Long stepId,
                             @ModelAttribute("course") CourseDto courseDto) {
        stepService.delete(stepId);
        return "redirect:/course/" + courseDto.getId();
    }
}