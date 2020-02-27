package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Person {
	private int id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String email;
	private Date dob;
	private Collection<Phone> phone;
	private Collection<Address> address;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfirst_name() {
		return first_name;
	}
	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getlast_name() {
		return last_name;
	}
	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Collection<Phone> getPhone() {
		return phone;
	}
	public void setPhone(Collection<Phone> phone) {
		this.phone = phone;
	}
	public void addPhone(Phone phone) {
        this.phone.add(phone);
    }
    public void removePhone(Phone phone) {
        this.phone.remove(phone);
    }
	public Collection<Address> getAddress() {
		return address;
	}
	public void setAddress(Collection<Address> address) {
		this.address = address;
	}
	public void addAddress(Address address) {
        this.address.add(address);
    }
    public void removeAddress(Address address) {
        this.address.remove(address);
    }
    
	public Person(int id, String first_name, String last_name, String username, String password, String email, Date dob,
			Collection<Phone> phone, Collection<Address> address) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
	}
    
    
    
	public Person(int id, String first_name, String last_name, String username, String password, String email, Date dob) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
    }

    public Person(int id, String first_name, String last_name) {
  	  	super();
    	this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
