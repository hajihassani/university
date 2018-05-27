package ir.university.api;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ir.university.entities.Person;
import ir.university.entities.Student;
import ir.university.managers.StudentManager;
import ir.university.model.StudentDto;

@Path("/students")
public class StudentResources {
	StudentManager studentMng = StudentManager.getInstance();

	@POST
	@Path("/addstudent")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String save(Student student) {
		try {
			return studentMng.save(student);
		} catch (SQLException e) {
			studentMng.logException(e.getMessage(), "Student save");
			return "Student do not saved!";
		}
	}

	@GET
	@Path("/student/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentDto load(@PathParam("id") int id) {
		try {
			return new StudentDto((Student) studentMng.load(id));
		} catch (SQLException e) {
			studentMng.logException(e.getMessage(), "Student load");
			return null;
		}
	}

	@POST
	@Path("/updstudent")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(Student student) {
		try {
			return studentMng.update(student);
		} catch (SQLException e) {
			studentMng.logException(e.getMessage(), "Student update");
			return "Cannot update the student!";
		}
	}

	@GET
	@Path("/dltstudent/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@PathParam("id") int id) {
		try {
			return studentMng.delete(id);
		} catch (SQLException e) {
			studentMng.logException(e.getMessage(), "Student delete");
			return "Cannot delete the student!";
		}
	}

	@GET
	@Path("/allstudents")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentDto[] findAll() {
		try {
			Person[] allStudents = studentMng.findAll();
			StudentDto[] students = new StudentDto[allStudents.length];
			for (int i = 0; i < students.length; i++) {
				students[i] = new StudentDto((Student) allStudents[i]);
			}
			return students;
		} catch (SQLException e) {
			studentMng.logException(e.getMessage(), "Student find all");
			return null;
		}
	}
}
