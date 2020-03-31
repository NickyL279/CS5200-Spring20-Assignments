package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DBConnection;

public class PrivilegeImpl {
	private static PrivilegeImpl instance = null;

	private PrivilegeImpl() {
	}

	public static PrivilegeImpl getInstance() {
		if (instance == null)
			instance = new PrivilegeImpl();
		return instance;
	}

	private PreparedStatement pStatement = null;

	private static String ASSIGN_WEBSITE_PRIVILEDGE = "INSERT INTO website_priviledge(priviledge, website, developer) VALUES (?, ?, ?)";
	private static String ASSIGN_PAGE_PRIVILEDGE = "INSERT INTO page_priviledge(priviledge, page, developer) VALUES (?, ?, ?)";
	private static String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM website_priviledge WHERE website_priviledge.priviledge = ? and website_priviledge.website = ? and website_priviledge.developer = ?";
	private static String DELETE_PAGE_PRIVILEDGE = "DELETE FROM page_priviledge WHERE page_priviledge.priviledge = ? and page_priviledge.page = ? and website_priviledge.developer = ?";

	public void assignWebsitePriviledge(int developerId, int websiteId, String priviledge) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(ASSIGN_WEBSITE_PRIVILEDGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, websiteId);
			pStatement.setInt(3, developerId);
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

	public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(ASSIGN_PAGE_PRIVILEDGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, pageId);
			pStatement.setInt(3, developerId);
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

	public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, websiteId);
			pStatement.setInt(3, developerId);
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

	public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(DELETE_PAGE_PRIVILEDGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, pageId);
			pStatement.setInt(3, developerId);
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
