package com.diploma.skillmaster.service;

import com.diploma.skillmaster.dto.CourseDto;
import com.diploma.skillmaster.mapper.CourseMapper;
import com.diploma.skillmaster.model.Course;
import com.diploma.skillmaster.model.UserEntity;
import com.diploma.skillmaster.repository.CourseRepository;
import com.diploma.skillmaster.repository.UserRepository;
import com.diploma.skillmaster.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing courses.
 */
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    /**
     * Retrieves all courses.
     *
     * @return A list of CourseDto objects representing the courses.
     */
    public List<CourseDto> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseMapper::mapToCourseDto).toList();
    }

    /**
     * Saves a course.
     *
     * @param courseDto The CourseDto object containing course details to be saved.
     */
    public void save(CourseDto courseDto) {
        String username = SecurityUtil.getSessionUser().orElseThrow();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        Course course = CourseMapper.mapToCourse(courseDto);
        course.setCreator(user);
        courseRepository.save(course);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param courseId The ID of the course to retrieve.
     * @return The CourseDto object representing the retrieved course.
     * @throws RuntimeException if the course is not found.
     */
    public CourseDto findById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return CourseMapper.mapToCourseDto(course);
    }

    /**
     * Deletes a course by its ID.
     *
     * @param courseId The ID of the course to delete.
     */
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    /**
     * Retrieves a list of courses by their name.
     *
     * @param keyword The name of the courses to retrieve.
     * @return A list of CourseDto objects representing the retrieved courses.
     */
    public List<CourseDto> findByKeyword(String keyword) {
        List<Course> allCourses = courseRepository.findAll();
        List<Course> matchingCourses = allCourses.stream()
                .filter(course -> course.getName().toLowerCase().contains(keyword.toLowerCase().trim()))
                .toList();
        return matchingCourses.stream().map(CourseMapper::mapToCourseDto).toList();
    }
}
