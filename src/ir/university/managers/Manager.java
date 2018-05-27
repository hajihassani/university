package ir.university.managers;

import java.sql.SQLException;

import ir.university.entities.Person;

public interface Manager {
	public String save(Person person) throws SQLException;

	public Person load(Integer id) throws SQLException;

	public String update(Person person) throws SQLException;

	public String delete(Integer id) throws SQLException;

	public Person[] findAll() throws SQLException;
}
