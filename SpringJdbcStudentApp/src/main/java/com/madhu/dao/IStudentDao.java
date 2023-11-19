package com.madhu.dao;

import java.util.List;

import com.madhu.modal.Student;

public interface IStudentDao {
List<Student> findAll();
Student findById(Integer id);
int addStudent(Student s);
int deleteStudent(Integer id);
int updateStudent(Integer id, String address);

}
