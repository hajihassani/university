package ir.university.dao;

import java.sql.SQLException;

import ir.university.entities.Person;

public interface Dao {
	public Boolean save(Person person) throws SQLException;

	public Person load(Integer id) throws SQLException;

	public Boolean update(Person person) throws SQLException;

	public Boolean delete(Integer id) throws SQLException;

	public Person[] findAll() throws SQLException;

	public abstract void logException(String msg, String type);
}
