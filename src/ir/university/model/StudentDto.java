package ir.university.model;

import ir.university.entities.Student;

public class StudentDto {
	private int id;
	private String fName;
	private String lName;
	private String dept;
	private TeacherDto teacher;

	public StudentDto(Student student) {
		this.id = student.getId();
		this.fName = student.getfName();
		this.lName = student.getlName();
		this.dept = student.getDept();
		this.teacher = new TeacherDto(student.getTeacher());
	}

	public StudentDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public TeacherDto getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDto teacher) {
		this.teacher = teacher;
	}

}
