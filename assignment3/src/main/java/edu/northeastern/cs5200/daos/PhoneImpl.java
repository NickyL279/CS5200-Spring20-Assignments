package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Phone;

public class PhoneImpl {
	public static PhoneImpl instance = null;

	private PhoneImpl() {

	}

	public static PhoneImpl getInstance() {
		if (instance == null) {
			instance = new PhoneImpl();
		}
		return instance;
	}

	private PreparedStatement pStatement = null;

	private final String CREATE_PHONE = "INSERT INTO phone (phone, `primary`, person_id) VALUES(?,?,?)";
	private final String UPDATE_PHONE = "UPDATE phone SET `phone` = ? WHERE person_id = ? AND `primary` = 1";
	private final String DELETE_PHONE = "DELETE FROM phone WHERE person_id = ? AND `primary` = ?";

	public int createPrimaryPhone(int personId, String phone) {
		int res = 0;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(CREATE_PHONE);
			pStatement.setString(1, phone);
//			pStatement.setBoolean(2, phone.isPrimary());
			pStatement.setInt(2, 1);
			pStatement.setInt(3, personId);

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

	public int updatePrimaryPhone(int personId, String phoneNumber) {
		int res = 0;
		try {

			pStatement = DBConnection.getConnection().prepareStatement(UPDATE_PHONE);
			pStatement.setString(1, phoneNumber);
//			pStatement.setBoolean(2, true);
			pStatement.setInt(2, personId);

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

	public void deletePrimaryPhone(int personId, Phone phone) {

		try {

			pStatement = DBConnection.getConnection().prepareStatement(DELETE_PHONE);
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
