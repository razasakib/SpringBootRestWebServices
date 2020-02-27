package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.model.Student;
import in.nit.repo.StudentRepository;
import in.nit.service.IStudentService;
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
	private StudentRepository repo; 

	public Integer saveStudent(Student s) {
		
		return repo.save(s).getStdId();
	}

	public List<Student> getAllStudents() {
		
		return repo.findAll();
	}
	
	
	public Optional<Student> getOneStudent(Integer id) {
	
		return repo.findById(id);
	}

	
	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}
	

	public void deleteStudent(Integer id) {
		repo.deleteById(id);		
	}

	
	@Override
	public void updateStudent(Student s) {
		 repo.saveAndFlush(s);
	}

}
