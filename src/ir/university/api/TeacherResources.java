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
import ir.university.entities.Teacher;
import ir.university.managers.TeacherManager;
import ir.university.model.TeacherDto;

@Path("/teachers")
public class TeacherResources {
	TeacherManager teacherMng = TeacherManager.getInstance();

	@POST
	@Path("/addteacher")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String save(Teacher teacher) {
		try {
			return teacherMng.save(teacher);
		} catch (SQLException e) {
			teacherMng.logException(e.getMessage(), "Teacher save");
			return "Teacher do not saved!";
		}
	}

	@GET
	@Path("/teacher/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TeacherDto load(@PathParam("id") int id) {
		try {
			return new TeacherDto((Teacher) teacherMng.load(id));
		} catch (SQLException e) {
			teacherMng.logException(e.getMessage(), "Teacher load");
			return null;
		}
	}

	@POST
	@Path("/updteacher")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(Teacher teacher){
		try {
			return teacherMng.update(teacher);
		} catch (SQLException e) {
			teacherMng.logException(e.getMessage(), "Teacher update");
			return "Cannot update the Teacher!";
		}
	}

	@GET
	@Path("/dltteacher/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@PathParam("id") int id) {
		try {
			return teacherMng.delete(id);
		} catch (SQLException e) {
			teacherMng.logException(e.getMessage(), "Teacher delete");
			return "Cannot delete the Teacher!";
		}
	}

	@GET
	@Path("/allteachers")
	@Produces(MediaType.APPLICATION_JSON)
	public TeacherDto[] findAll() {
		try {
			Person[] allTeachers = teacherMng.findAll();
			TeacherDto[] teachers = new TeacherDto[allTeachers.length];
			for (int i = 0; i < teachers.length; i++) {
				teachers[i] = new TeacherDto((Teacher) allTeachers[i]);
			}
			return teachers;
		} catch (SQLException e) {
			teacherMng.logException(e.getMessage(), "Teacher find all");
			return null;
		}
	}
}
