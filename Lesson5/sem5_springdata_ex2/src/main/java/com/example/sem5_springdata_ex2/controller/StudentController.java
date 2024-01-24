package com.example.sem5_springdata_ex2.controller;

import com.example.sem5_springdata_ex2.domain.Course;
import com.example.sem5_springdata_ex2.domain.Student;
import com.example.sem5_springdata_ex2.exception.ResourceNotFoundException;
import com.example.sem5_springdata_ex2.repository.CourseRepository;
import com.example.sem5_springdata_ex2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public Student addCourseToStudent(@PathVariable Long studentId,
                                      @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Student not found with id: " + studentId)); // .get();
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("Course not found with id: " + courseId)); //.get();
        student.getCourses().add(course);
        return studentRepository.save(student);
    }

    // другие методы для удаления курса у студента и т.д.
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public Student removeCourseFromStudent(@PathVariable Long studentId,
                                           @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("Course not found with id: " + courseId));
        student.getCourses().remove(course);
        return studentRepository.save(student);
    }

    /**
     * В этом методе используется аннотация @DeleteMapping и пути "/students/{studentId}/courses/{courseId}",
     * которые указывают на URL-шаблон для удаления курса у студента.
     *
     * Внутри метода:
     *
     * 1. Сначала по studentId находится объект студента вызовом studentRepository.findById(studentId).
     * В случае, если запись о студенте не найдена, выбрасывается исключение ResourceNotFoundException.
     *
     * 2. Затем по courseId находится объект курса вызовом courseRepository.findById(courseId).
     * В случае, если запись о курсе не найдена, выбрасывается исключение ResourceNotFoundException.
     *
     * 3. Далее происходит удаление курса из списка курсов студента вызовом student.getCourses().remove(course).
     *
     * 4. В конце метода вызывается studentRepository.save(student) для сохранения изменений в базе данных.
     */


}
