package com.diploma.skillmaster.service;

import com.diploma.skillmaster.dto.CourseDto;
import com.diploma.skillmaster.mapper.CourseMapper;
import com.diploma.skillmaster.model.Course;
import com.diploma.skillmaster.model.UserEntity;
import com.diploma.skillmaster.repository.CourseRepository;
import com.diploma.skillmaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.diploma.skillmaster.mapper.CourseMapper.mapToCourse;
import static com.diploma.skillmaster.mapper.CourseMapper.mapToCourseDto;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<CourseDto> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseMapper::mapToCourseDto).toList();
    }

    //  WILL BE UNCOMMENTED AFTER SECURITY SETUP //

//    public void saveOrUpdateCourse(CourseDto courseDto) {
//        String username = SecurityUtil.getSessionUser().orElseThrow();
//        UserEntity user = userRepository.findByUsername(username).orElseThrow();
//        Course course = mapToCourse(courseDto);
//        course.setCreatedBy(user);
//        courseRepository.save(course);
//    }

    public CourseDto findCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        return mapToCourseDto(course);
    }

    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<CourseDto> searchCourses(String query) {
        List<Course> courses = courseRepository.searchCourseByNameLike(query);
        return courses.stream().map(CourseMapper::mapToCourseDto).toList();
    }
}
