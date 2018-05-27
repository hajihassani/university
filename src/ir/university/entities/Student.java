package ir.university.entities;

public class Student extends Person {

	private String dept;
	private Teacher teacher;

	public Student() {
		super();
	}

	public Student(int id, String fName, String lName, String dept, Teacher teacher) {
		super(id, fName, lName);
		this.dept = dept;
		this.teacher = teacher;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
