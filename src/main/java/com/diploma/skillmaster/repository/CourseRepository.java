package com.diploma.skillmaster.repository;

import com.diploma.skillmaster.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> searchCourseByNameLike(String title);
}