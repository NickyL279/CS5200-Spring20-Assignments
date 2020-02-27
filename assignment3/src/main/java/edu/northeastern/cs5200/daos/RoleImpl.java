package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DBConnection;

public class RoleImpl {
	private static RoleImpl instance = null;

	private RoleImpl() {

	}

	public static RoleImpl getInstance() {
		if (instance == null) {
			instance = new RoleImpl();
		}
		return instance;
	}

	private PreparedStatement pStatement = null;

	private final String CREATE_WEB_Role = "INSERT INTO website_role (role, developer_id, website_id) VALUES (?,?,?)";
	private final String CREATE_PAGE_Role = "INSERT INTO page_role (role, developer_id, page_id) VALUES (?,?,?)";
	private final String DELETE_WEB_Role = "DELETE from website_role where role = ? and developer_id =? and website_id = ?";
	private final String DELETE_PAGE_Role = "DELETE from page_role where role = ? and developer_id =? and page_id = ?";

	public void assignWebsiteRole(int developerId, int websiteId, String Role) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(CREATE_WEB_Role);
			pStatement.setString(1, Role);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, websiteId);
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

	public void assignPageRole(int developerId, int pageId, String Role) {

		int res = 0;

		try {

			pStatement = DBConnection.getConnection().prepareStatement(CREATE_PAGE_Role);
			pStatement.setString(1, Role);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);

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
	}

	public void deleteWebsiteRole(int developerId, int websiteId, int Role) {

		int res = 0;

		try {

			pStatement = DBConnection.getConnection().prepareStatement(DELETE_WEB_Role);
			pStatement.setInt(1, Role);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, websiteId);

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
	}

	public void deletePageRole(int developerId, int pageId, String Role) {

		int res = 0;

		try {

			pStatement = DBConnection.getConnection().prepareStatement(DELETE_PAGE_Role);
			pStatement.setString(1, Role);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);

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
	}
}
