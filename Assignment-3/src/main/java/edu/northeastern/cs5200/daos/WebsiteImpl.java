package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Website;

public class WebsiteImpl implements WebsiteDao {
	private static WebsiteImpl instance = null;

	private WebsiteImpl() {
	}

	public static WebsiteImpl getInstance() {
		if (instance == null) {
			instance = new WebsiteImpl();
		}
		return instance;
	}

	private PreparedStatement pStatement = null;

	private final String CREATE_WEBSITE = "INSERT INTO website (id, name, description, created, updated, visits) VALUES (?, ?, ?, ?, ?, ?)";
	private final String FIND_ALLWEBSITES = "SELECT * FROM website";
	private final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM website web JOIN WebsiteRole webr ON web.id = webr.website_id WHERE developer_id = ?";
	private final String FIND_WEBSITES_BY_ID = "SELECT * FROM website WHERE id=?";
	
	// private final String DELETE_WEBSITE_ROLE = "DELETE FROM WebsiteRole WHERE
	// website_id=?";
	// private final String DELETE_PRIVILEDGE = "DELETE FROM website_priviledge
	// where website_id = ?";
	

	public void createWebsiteForDeveloper(int developerId, Website website) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(CREATE_WEBSITE);
			pStatement.setInt(1, website.getId());
			pStatement.setString(2, website.getName());
			pStatement.setString(3, website.getDescription());
			pStatement.setDate(4, website.getCreated());
			pStatement.setDate(5, website.getUpdated());
			pStatement.setInt(6, website.getVisits());
			//pStatement.setInt(7, developerId);
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

	public Collection<Website> findAllWebsites() {
		ResultSet results = null;
		Collection<Website> websites = new ArrayList<Website>();
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_ALLWEBSITES);
			results = pStatement.executeQuery(FIND_ALLWEBSITES);
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				int developerId = results.getInt("developer_id");
				// int developerId = -1;
				Website web = new Website(id, name, description, created, updated, visits, developerId);

				websites.add(web);
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
		return websites;
	}

	public Collection<Website> findWebsitesForDeveloper(int developerId) {
		ResultSet results = null;
		Collection<Website> websites = new ArrayList<Website>();
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
			pStatement.setInt(1, developerId);
			results = pStatement.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				results.getInt("developer_id");
				// int developerId1 = -1;
				Website web = new Website(id, name, description, created, updated, visits, developerId);
				websites.add(web);
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
		return websites;
	}

	public Website findWebsiteById(int websiteId) {
		ResultSet results = null;
		Website web = null;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_WEBSITES_BY_ID);
			pStatement.setInt(1, websiteId);
			results = pStatement.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				int developerId = -1;
				// results.getInt("developerId");
				web = new Website(id, name, description, created, updated, visits, developerId);
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
		return web;
	}
	
	private final String UPDATE_WEBSITE = "UPDATE website SET `name`=?, description=?, created=?, updated=?, visits=? WHERE id=?";
	
	public int updateWebsite(int websiteId, Website website) {
		int resultn = 0;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(UPDATE_WEBSITE);
			pStatement.setInt(1, website.getId());
			pStatement.setString(2, website.getName());
			pStatement.setString(3, website.getDescription());
			pStatement.setDate(4, website.getCreated());
			pStatement.setDate(5, website.getUpdated());
			pStatement.setInt(6, website.getVisits());
			// statement.setInt(7, website.getDeveloperId());
			//pStatement.setInt(7, websiteId);
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
	
	private final String DELETE_WEBSITE = "DELETE FROM website WHERE id=?";
	
	public int deleteWebsite(int websiteId) {
		int resultn = 0;

		try {
			pStatement = DBConnection.getConnection().prepareStatement(DELETE_WEBSITE);
			pStatement.setInt(1, websiteId);
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

}
