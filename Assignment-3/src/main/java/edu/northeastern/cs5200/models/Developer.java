package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Developer extends Person{
	private String developer_Key;

	public String getDeveloper_Key() {
		return developer_Key;
	}

	public void setDeveloper_Key(String developer_Key) {
		this.developer_Key = developer_Key;
	}

	public Developer(int id, String first_name, String last_name, String username, String password, String email,
			Date dob, Collection<Phone> phone, Collection<Address> address, String developer_Key) {
		super(id, first_name, last_name, username, password, email, dob, phone, address);
		this.developer_Key = developer_Key;
	}

	public Developer(int id, String first_name, String last_name, String username, String password, String email,
			Date dob, String developer_Key) {
		super(id, first_name, last_name, username, password, email, dob);
		this.developer_Key = developer_Key;
	}

	public Developer(int id, String first_name, String last_name, String developer_Key) {
		super(id, first_name, last_name);
		this.developer_Key = developer_Key;
	}
	
	
}
