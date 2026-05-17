package com.example.course.repository;

import com.example.course.models.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorRepository {
    private List<Instructor> instructors;

    public InstructorRepository() {
        instructors = new ArrayList<>();

        instructors.add(new Instructor(1L, "Nguyen Van A", "a@gmail.com"));
        instructors.add(new Instructor(2L, "Tran Thi B", "b@gmail.com"));
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }
    public Optional<Instructor> getInstructorById(Long id){
        return instructors.stream().filter(instructor -> instructor.getId() == id).findFirst();

    }
    public Instructor addInstructor(Instructor instructor){
        instructors.add(instructor);
        return instructor;
    }
    public Instructor updateInstructor(Long id, Instructor instructor){
        Instructor instructorFound = getInstructorById(id).orElseThrow(() -> new RuntimeException("instructor not found"));
        instructorFound.setEmail(instructor.getEmail());
        instructorFound.setPassword(instructor.getPassword());
        return instructorFound;
    }


    public void deleteInstructor(Long id){
        for (int i = 0; i < instructors.size(); i++) {
            if(instructors.get(i).getId() == id){
                instructors.remove(i);
            }
        }
    }
}
