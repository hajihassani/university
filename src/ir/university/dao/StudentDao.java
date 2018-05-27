package ir.university.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ir.university.entities.Person;
import ir.university.entities.Student;
import ir.university.entities.Teacher;

public class StudentDao extends DaoImp {
	private static StudentDao instance;
	private Dao teacherOfStudent = TeacherDao.getInstance();

	private StudentDao() {
		super();
	}

	public static StudentDao getInstace() {
		if (instance == null) {
			instance = new StudentDao();
		}
		return instance;
	}

	@Override
	public Boolean save(Person person) throws SQLException {
		Student student = null;
		if (person instanceof Student) {
			student = (Student) person;
		}
		String query = "INSERT INTO student(id,fname,lname,department,teacher_id) VALUES(?,?,?,?,?)";
		ps = connection.prepareStatement(query);
		ps.setInt(1, student.getId());
		ps.setString(2, student.getfName());
		ps.setString(3, student.getlName());
		ps.setString(4, student.getDept());
		ps.setInt(5, student.getTeacher().getId());
		ps.executeUpdate();
		return true;
	}

	@Override
	public Person load(Integer id) throws SQLException {
		String query = "SELECT * FROM student WHERE id=?";
		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setfName(rs.getString("fname"));
		student.setlName(rs.getString("lname"));
		student.setDept(rs.getString("department"));
		student.setTeacher((Teacher) teacherOfStudent.load(rs.getInt("teacher_id")));
		return student;
	}

	@Override
	public Boolean update(Person person) throws SQLException {
		Student student = null;
		if (person instanceof Student) {
			student = (Student) person;
		}
		String query = "UPDATE student SET fname=?, lname=?, department=?, teacher_id=? WHERE id=?";
		ps = connection.prepareStatement(query);
		ps.setString(1, student.getfName());
		ps.setString(2, student.getlName());
		ps.setString(3, student.getDept());
		ps.setInt(4, student.getTeacher().getId());
		ps.setInt(5, student.getId());
		ps.executeUpdate();
		return true;
	}

	@Override
	public Boolean delete(Integer id) throws SQLException {
		String query = "DELETE FROM student WHERE id=?";
		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
		return true;
	}

	@Override
	public Person[] findAll() throws SQLException {
		String query = "SELECT * FROM student";
		ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		rs.last();
		int length = rs.getRow();
		rs.beforeFirst();
		Student[] students = new Student[length];
		int i = 0;
		while (rs.next()) {
			students[i] = new Student();
			students[i].setId(rs.getInt("id"));
			students[i].setfName(rs.getString("fname"));
			students[i].setlName(rs.getString("lname"));
			students[i].setDept(rs.getString("department"));
			students[i].setTeacher((Teacher) teacherOfStudent.load(rs.getInt("teacher_id")));
			i++;
		}
		return students;
	}

	@Override
	public void logException(String msg, String type) {
		try {
			String query = "INSERT INTO logs(msg,type) VALUES(?,?)";
			ps = connection.prepareStatement(query);
			ps.setString(1, msg);
			ps.setString(2, type);
			ps.executeUpdate();
		} catch (Exception e) {

		}
	}

}
