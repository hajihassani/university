package ir.university.model;

import ir.university.entities.Teacher;

public class TeacherDto {
	private int id;
	private String fName;
	private String lName;
	private String address;

	public TeacherDto(Teacher teacher) {
		this.id = teacher.getId();
		this.fName = teacher.getfName();
		this.lName = teacher.getlName();
		this.address = teacher.getAddress();
	}

	public TeacherDto() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
