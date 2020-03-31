package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Address;

public class AddressImpl {
	public static AddressImpl instance = null;

	private AddressImpl() {

	}

	public static AddressImpl getInstance() {
		if (instance == null) {
			instance = new AddressImpl();
		}
		return instance;
	}

	private PreparedStatement pStatement = null;

	private final String CREATE_ADDRESS = "INSERT INTO address (street1, street2, city, state, zip, `primary`, person_id) VALUES(?,?,?,?,?,?,?)";
	private final String UPDATE_ADDRESS = "UPDATE address SET street1 = ?, street2 = ?,city= ?, state = ?, zip =? Where person_id = ? and `primary` = ? ";
	private final String DELETE_ADDRESS = "DELETE FROM address WHERE person_id = ? AND `primary` = ?";

	public void addAddress(int personId, Address address) {

		try {

			pStatement = DBConnection.getConnection().prepareStatement(CREATE_ADDRESS);
			pStatement.setString(1, address.getStreet1());
			pStatement.setString(2, address.getStreet2());
			pStatement.setString(3, address.getCity());
			pStatement.setString(4, address.getState());
			pStatement.setString(5, address.getZip());
			pStatement.setBoolean(6, address.getPrimary());
			pStatement.setInt(7, personId);
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

	public void updatePrimaryAddress(int personId, Address address) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(UPDATE_ADDRESS);
			pStatement.setString(1, address.getStreet1());
			pStatement.setString(2, address.getStreet2());
			pStatement.setString(3, address.getCity());
			pStatement.setString(4, address.getState());
			pStatement.setString(5, address.getZip());
			pStatement.setInt(6, personId);
			pStatement.setBoolean(7, true);
			//int res = pStatement.executeUpdate();
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

	public void deletePrimaryAddress(int personId, Address address) {

		try {

			pStatement = DBConnection.getConnection().prepareStatement(DELETE_ADDRESS);
			pStatement.setBoolean(1, true);
			pStatement.setInt(2, personId);
			//int res = pStatement.executeUpdate();
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
