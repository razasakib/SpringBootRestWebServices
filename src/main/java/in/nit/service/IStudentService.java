package in.nit.service;

import java.util.List;
import java.util.Optional;

import in.nit.model.Student;

public interface IStudentService {
   public Integer saveStudent(Student s);
   public List<Student> getAllStudents();
   public Optional<Student> getOneStudent(Integer id);
   public boolean isExist(Integer id);
   public void deleteStudent(Integer id);
   public void updateStudent(Student s);

}
