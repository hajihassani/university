package ir.university.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ir.university.entities.Person;
import ir.university.entities.Teacher;

public class TeacherDao extends DaoImp {
	private static TeacherDao instance;

	private TeacherDao() {
		super();
	}

	public static TeacherDao getInstance() {
		if (instance == null) {
			instance = new TeacherDao();
		}
		return instance;
	}

	@Override
	public Boolean save(Person person) throws SQLException {
		Teacher teacher = null;
		if (person instanceof Teacher) {
			teacher = (Teacher) person;
		}
		String query = "INSERT INTO teacher(id,fname,lname,address) VALUES(?,?,?,?)";
		ps = connection.prepareStatement(query);
		ps.setInt(1, teacher.getId());
		ps.setString(2, teacher.getfName());
		ps.setString(3, teacher.getlName());
		ps.setString(4, teacher.getAddress());
		ps.executeUpdate();
		return true;
	}

	@Override
	public Person load(Integer id) throws SQLException {
		String query = "SELECT * FROM teacher WHERE id=?";
		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Teacher teacher = new Teacher();
		teacher.setId(rs.getInt("id"));
		teacher.setfName(rs.getString("fname"));
		teacher.setlName(rs.getString("lname"));
		teacher.setAddress(rs.getString("address"));
		return teacher;
	}

	@Override
	public Boolean update(Person person) throws SQLException {
		Teacher teacher = null;
		if (person instanceof Teacher) {
			teacher = (Teacher) person;
		}
		String query = "UPDATE teacher SET fname=?, lname=?, address=? WHERE id=?";
		ps = connection.prepareStatement(query);
		ps.setString(1, teacher.getfName());
		ps.setString(2, teacher.getlName());
		ps.setString(3, teacher.getAddress());
		ps.setInt(4, teacher.getId());
		ps.executeUpdate();
		return true;
	}

	@Override
	public Boolean delete(Integer id) throws SQLException {
		String query = "DELETE FROM teacher WHERE id=?";
		ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
		return true;
	}

	@Override
	public Person[] findAll() throws SQLException {
		String query = "SELECT * FROM teacher";
		ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		rs.last();
		int length = rs.getRow();
		rs.beforeFirst();
		Teacher[] teachers = new Teacher[length];
		int i = 0;
		while (rs.next()) {
			teachers[i] = new Teacher();
			teachers[i].setId(rs.getInt("id"));
			teachers[i].setfName(rs.getString("fname"));
			teachers[i].setlName(rs.getString("lname"));
			teachers[i].setAddress(rs.getString("address"));
			i++;
		}
		return teachers;
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
