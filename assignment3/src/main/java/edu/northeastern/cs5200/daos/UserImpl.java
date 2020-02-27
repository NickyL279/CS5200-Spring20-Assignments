package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.User;

public class UserImpl {
	private static UserImpl instance = null;

	private UserImpl() {

	}

	public static UserImpl getInstance() {
		if (instance == null) {
			instance = new UserImpl();
		}
		return instance;
	}

//	UserImpl phoneimpl = UserImpl.getInstance();
//	AddressImpl addressimpl = AddressImpl.getInstance();

	private PreparedStatement pStatement = null;
	private final String CREATE_PERSON = "INSERT INTO person (id, first_name, last_name, username, password, email, dob) VALUES(?,?,?,?,?,?,?)";
	private final String CREATE_USER = "INSERT INTO user (id, user_agreement) VALUES(?,?)";

	public void createUser(User user) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(CREATE_PERSON);
			pStatement.setInt(1, user.getId());
			pStatement.setString(2, user.getfirst_name());
			pStatement.setString(3, user.getlast_name());
			pStatement.setString(4, user.getUsername());
			pStatement.setString(5, user.getPassword());
			pStatement.setString(6, user.getEmail());
			pStatement.setDate(7, user.getDob());
			pStatement.executeUpdate();

			pStatement = DBConnection.getConnection().prepareStatement(CREATE_USER);
			pStatement.setInt(1, user.getId());
			pStatement.setBoolean(2, user.getUser_agreement());
			pStatement.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement.close();
				DBConnection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
