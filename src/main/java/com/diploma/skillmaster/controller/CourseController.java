package com.diploma.skillmaster.controller;

import com.diploma.skillmaster.dto.CourseDto;
import com.diploma.skillmaster.model.UserEntity;
import com.diploma.skillmaster.security.SecurityUtil;
import com.diploma.skillmaster.service.CourseService;
import com.diploma.skillmaster.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling course-related requests.
 */
@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;

    /**
     * Handles the GET request for retrieving all courses.
     * Retrieves all courses from the course service, sorts them, and adds them to the model.
     *
     * @param model the model object for passing data to the view
     * @return the name of the view template to render
     */
    @GetMapping
    public String getAllCourses(Model model) {
        List<CourseDto> courses = courseService.findAll().stream().sorted().toList();
        model.addAttribute("courses", courses);
        return "courses-list";
    }

    /**
     * Handles the GET request for searching courses by name.
     * Retrieves courses from the course service that match the provided name and adds them to the model.
     *
     * @param name  the name of the course to search for
     * @param model the model object for passing data to the view
     * @return the name of the view template to render
     */
    @GetMapping("/search")
    public String getCoursesByName(@RequestParam(value = "name") String name, Model model){
        List<CourseDto> courses = courseService.findByName(name);
        model.addAttribute("courses", courses);
        return "courses-list";
    }

    /**
     * Handles the GET request for retrieving a specific course by ID.
     * Retrieves the course with the specified ID from the course service.
     *
     * @param courseId the ID of the course to retrieve
     * @return the name of the view template to render
     */
    @GetMapping("/{courseId:\\d+}")
    public String getCourse(@PathVariable Long courseId, Model model) {
        CourseDto courseDto = courseService.findById(courseId);
        model.addAttribute("course", courseDto);

        Optional<String> username = SecurityUtil.getSessionUser();
        UserEntity user = new UserEntity();
        if (username.isPresent()) {
            user = userService.findByUsername(username.get());
        }
        model.addAttribute("user", user);

        return "course-detail";
    }

    /**
     * Handles the GET request for "/save" endpoint.
     * Displays the course save form.
     *
     * @param model the model object for passing data to the view
     * @return the name of the view template to render
     */
    @GetMapping("/new")
    public String saveCourseForm(Model model) {
        CourseDto courseDto = new CourseDto();
        model.addAttribute("course", courseDto);
        model.addAttribute("courseId", courseDto.getId()); //WTF
        return "course-save";
    }

    /**
     * Handles the GET request for "/{courseId}/edit" endpoint.
     * Displays the course edit form.
     *
     * @param courseId the ID of the course to edit
     * @param model    the model object for passing data to the view
     * @return the name of the view template to render
     */
    @GetMapping("/{courseId:\\d+}/edit")
    public String editCourseForm(@PathVariable("courseId") Long courseId, Model model) {
        CourseDto courseDto = courseService.findById(courseId);
        model.addAttribute("course", courseDto);
        model.addAttribute("courseId", courseId); //WTF
        return "course-save";
    }

    /**
     * Handles the POST request for "/save" endpoint.
     * Saves the course data.
     *
     * @param courseDto  the CourseDto object containing the course data
     * @param courseId   the ID of the course being edited
     * @param mode       the mode of operation ("EDIT" or "CREATE")
     * @param result     the BindingResult object for validation errors
     * @param model      the model object for passing data to the view
     * @return the name of the view template to render or a redirect URL
     */
    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") CourseDto courseDto,
                                 @ModelAttribute("courseId") Long courseId,
                                 @RequestParam("mode") String mode,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("course", courseDto);
            return "course-save";
        }

        if (mode.equals("EDIT")) {
            courseDto.setId(courseId);
        }

        courseService.save(courseDto);
        return "redirect:/course";
    }

    /**
     * Handles the GET request for "/{courseId}/delete" endpoint.
     * Deletes the course with the specified ID.
     *
     * @param courseId the ID of the course to delete
     * @return a redirect URL to navigate after deleting the course
     */
    @GetMapping("/{courseId:\\d+}/delete")
    public String deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.delete(courseId);
        return "redirect:/course";
    }
}