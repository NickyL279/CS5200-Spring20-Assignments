package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Developer;

public class DeveloperImpl implements DeveloperDao {
	private static DeveloperImpl instance = null;

	private DeveloperImpl() {
	}

	public static DeveloperImpl getInstance() {
		if (instance == null) {
			instance = new DeveloperImpl();
		}
		return instance;
	}

	private PreparedStatement pStatement = null;

	private final String CREATE_PERSON = "INSERT INTO person (id, first_name, last_name, username, password, email, dob) VALUES(?,?,?,?,?,?,?)";
	private final String CREATE_DEVELOPER = "INSERT INTO developer (id, developer_key) VALUES(?, ?)";

	private final String FIND_ALL_DEVELOPERS = "SELECT * FROM developer WHERE person.id = developer.id";
	private final String FIND_DEVELOPER_BY_ID = "SELECT * FROM person, developer WHERE person.id = developer.id";//
	// private final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM person,
	// developer WHERE person.username = ? AND person.id = developer.id";
	private final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM person, developer WHERE person.username = ? AND person.id = developer.id";
	private final String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM developer, person WHERE person.id = developer.id AND password = ? AND username = ?";

	private final String UPDATE_PERSON = "UPDATE Person SET first_name = ?, last_name = ?, username = ?, password = ?, email = ?, dob = ? WHERE person.id = ?";
	private final String UPDATE_DEVELOPER = "UPDATE Developer SET developer_key=? WHERE id=?";

	private final String DELETE_PERSON = "DELETE FROM person WHERE id=?";
	private final String DELETE_DEVELOPER = "DELETE FROM Developer WHERE person_id=?";

	/*---   ---*/
	public void createDeveloper(Developer developer) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(CREATE_PERSON);
			pStatement.setInt(1, developer.getId());
			pStatement.setString(2, developer.getfirst_name());
			pStatement.setString(3, developer.getlast_name());
			pStatement.setString(4, developer.getUsername());
			pStatement.setString(5, developer.getPassword());
			pStatement.setString(6, developer.getEmail());
			pStatement.setDate(7, developer.getDob());
			pStatement.executeUpdate();

			pStatement = DBConnection.getConnection().prepareStatement(CREATE_DEVELOPER);
			pStatement.setInt(1, developer.getId());
			pStatement.setString(2, developer.getDeveloper_Key());
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

	/*---   ---*/
	public Collection<Developer> findAllDevelopers() {
		Collection<Developer> developers = new ArrayList<Developer>();
		ResultSet result = null;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_ALL_DEVELOPERS);
			result = pStatement.executeQuery();
			while (result.next()) {
				int id = result.getInt("person_id");
				String first_name = result.getString("first_name");
				String last_name = result.getString("last_name");
				String userName = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String developer_key = result.getString("developer_key");
				Date dob = result.getDate("dob");

				Developer dev = new Developer(id, first_name, last_name, userName, password, email, dob, developer_key);
				developers.add(dev);
			}
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
		return developers;
	}

	/*---   ---*/
	public Developer findDeveloperById(int developerId) {
		Developer dev = null;
		ResultSet result = null;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_DEVELOPER_BY_ID);
			pStatement.setInt(1, developerId);
			result = pStatement.executeQuery();
			if (result.next()) {
				String developer_key = result.getString("developer_key");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				int id = result.getInt("id");

				dev = new Developer(id, firstName, lastName, username, password, email, dob, developer_key);
			}

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
		return dev;
	}

	/*---   ---*/
	public Developer findDeveloperByUsername(String username) {
		Developer dev = null;
		ResultSet result = null;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_DEVELOPER_BY_USERNAME);
			pStatement.setString(1, username);
			result = pStatement.executeQuery();
			while (result.next()) {
				String developer_key = result.getString("developer_key");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				int id = result.getInt("id");

				dev = new Developer(id, firstName, lastName, username, password, email, dob, developer_key);
			}
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
		return dev;
	}

	public Developer findDeveloperByCredentials(String username, String password) {
		Developer dev = null;
		ResultSet result = null;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			result = pStatement.executeQuery();
			while (result.next()) {
				String developerKey = result.getString("developer_key");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				int id = result.getInt("id");

				dev = new Developer(id, firstName, lastName, username, password, email, dob, developerKey);
			}
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
		return dev;
	}

	public int updateDeveloper(int developerId, Developer developer) {
		int resultn = 0;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(UPDATE_PERSON);

			pStatement.setString(1, developer.getfirst_name());
			pStatement.setString(2, developer.getlast_name());
			pStatement.setString(3, developer.getUsername());
			pStatement.setString(4, developer.getPassword());
			pStatement.setString(5, developer.getEmail());
			java.sql.Date dobSql;
			dobSql = new java.sql.Date(developer.getDob().getTime());
			pStatement.setDate(6, dobSql);
			pStatement.setInt(7, developerId);
			resultn = pStatement.executeUpdate();

			pStatement = DBConnection.getConnection().prepareStatement(UPDATE_DEVELOPER);
			pStatement.setString(1, developer.getDeveloper_Key());
			pStatement.setInt(2, developerId);
			resultn = pStatement.executeUpdate();
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
		return resultn;
	}

	public int deleteDeveloper(int developerId) {
		int resultn = 0;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(DELETE_DEVELOPER);
			pStatement.setInt(1, developerId);
			pStatement.executeUpdate();
			pStatement = DBConnection.getConnection().prepareStatement(DELETE_PERSON);
			pStatement.setInt(1, developerId);
			resultn = pStatement.executeUpdate();

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
		return resultn;
	}

	private static String DELETE_PRIMARY_ADDRESS = "DELETE FROM address WHERE address.`primary` = 1 and address.person_id = ?";

	public int deletePrimaryAddress(int personId) {
		int res = 0;
		try {

			pStatement = DBConnection.getConnection().prepareStatement(DELETE_PRIMARY_ADDRESS);
			pStatement.setInt(1, personId);

			res = pStatement.executeUpdate();
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
		return res;
	}

}
