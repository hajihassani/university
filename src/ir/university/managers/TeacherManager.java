package ir.university.managers;

import java.sql.SQLException;

import ir.university.dao.Dao;
import ir.university.dao.TeacherDao;
import ir.university.entities.Person;
import ir.university.entities.Teacher;

public class TeacherManager implements Manager {
	private static TeacherManager instance;
	private Dao teacher = TeacherDao.getInstance();

	private TeacherManager() {

	}

	public static TeacherManager getInstance() {
		if (instance == null) {
			instance = new TeacherManager();
		}
		return instance;
	}

	@Override
	public String save(Person person) throws SQLException {
		if (teacher.save(person)) {
			return "Teacher Added Successfully.";
		} else {
			return "Teacher Adding Rejected.";
		}
	}

	@Override
	public Person load(Integer id) throws SQLException {
		return teacher.load(id);
	}

	@Override
	public String update(Person person) throws SQLException {
		if (teacher.update(person)) {
			return "Teacher Updated Succesfully.";
		} else {
			return "Teacher Updating Rejected.";
		}
	}

	@Override
	public String delete(Integer id) throws SQLException {
		if (teacher.delete(id)) {
			return "Teacher Deleted Succesfully.";
		} else {
			return "Teacher Deleting Rejected.";
		}
	}

	@Override
	public Person[] findAll() throws SQLException {
		Teacher[] teachers = (Teacher[]) teacher.findAll();
		return teachers;
	}

	public void logException(String msg, String type) {
		teacher.logException(msg, type);
	}

}
