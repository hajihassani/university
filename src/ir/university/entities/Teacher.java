package ir.university.entities;

public class Teacher extends Person {
	private String address;

	public Teacher() {
		super();
	}

	public Teacher(int id, String fName, String lName, String address) {
		super(id, fName, lName);
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
