package in.nit.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nit.model.Student;
import in.nit.service.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentRestController {
	@Autowired
	private IStudentService service;

	/* save */
	@PostMapping("/save")
	public ResponseEntity<String> saveStudent(@RequestBody Student student) {
		ResponseEntity<String> res = null;
		try {
			Integer id = service.saveStudent(student);
			res = new ResponseEntity<String>(id + "-Saved", HttpStatus.OK);

		} catch (Exception e) {
			res = new ResponseEntity<String>("Unable to Saved", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return res;
	}

	/* getAllStudent */
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudents() {

		ResponseEntity<?> res = null;
		try {
			List<Student> list = service.getAllStudents();
			if (list != null && !list.isEmpty()) {
				res = new ResponseEntity<List<Student>>(list, HttpStatus.OK);
			} else {
				res = new ResponseEntity<String>("No Data Found", HttpStatus.OK);
			}
		} catch (Exception e) {
			res = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return res;
	}

	/* get OneStudent Data */
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneStudentData(@PathVariable Integer id) {
		ResponseEntity<?> res = null;
		try {
			Optional<Student> opt = service.getOneStudent(id);
			if (opt.isPresent()) {
				res = new ResponseEntity<Student>(opt.get(), HttpStatus.OK);
			} else {
				res = new ResponseEntity<String>("No Data Found", HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			res = new ResponseEntity<String>("Unable to Fetch Data", HttpStatus.INTERNAL_SERVER_ERROR);

			e.printStackTrace();
		}
		return res;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteStudent(
			@PathVariable Integer id)
	{
		ResponseEntity<String> res=null;
		try {
			boolean exist=service.isExist(id);
			if(exist) {
				service.deleteStudent(id);
				res=new ResponseEntity<String>(id+"-removed",HttpStatus.OK);
						
			}else {
				res=new ResponseEntity<String>(id+"-Not Exist",HttpStatus.BAD_REQUEST);
						
			}
		} catch (Exception e) {
			res=new ResponseEntity<String>("Unable to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
					
					e.printStackTrace();
		}
		return res;
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student){
		ResponseEntity<String> res=null;
		//check id is exits
		 boolean present=service.isExist(student.getStdId());
		if(present) {
			service.updateStudent(student);
			res=new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
		}else {
			res=new ResponseEntity<String>(student.getStdId()+"not present",HttpStatus.BAD_REQUEST);
		}
		
		return res;
	}

}