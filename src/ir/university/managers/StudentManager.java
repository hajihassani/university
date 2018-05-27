package ir.university.managers;

import java.sql.SQLException;

import ir.university.dao.Dao;
import ir.university.dao.StudentDao;
import ir.university.entities.Person;
import ir.university.entities.Student;

public class StudentManager implements Manager {
	private static StudentManager instance;
	private Dao student = StudentDao.getInstace();

	private StudentManager() {

	}

	public static StudentManager getInstance() {
		if (instance == null) {
			instance = new StudentManager();
		}
		return instance;
	}

	@Override
	public String save(Person person) throws SQLException {
		if (student.save(person)) {
			return "Student Added Successfully.";
		} else {
			return "Student Adding Rejected.";
		}
	}

	@Override
	public Person load(Integer id) throws SQLException {
		return student.load(id);
	}

	@Override
	public String update(Person person) throws SQLException {
		if (student.update(person)) {
			return "Student Updated Succesfully.";
		} else {
			return "Student Updating Rejected.";
		}
	}

	@Override
	public String delete(Integer id) throws SQLException {
		if (student.delete(id)) {
			return "Student Deleted Succesfully.";
		} else {
			return "Student Deleting Rejected.";
		}
	}

	@Override
	public Person[] findAll() throws SQLException {
		Student[] students = (Student[]) student.findAll();
		return students;
	}

	public void logException(String msg, String type) {
		student.logException(msg, type);
	}
}
