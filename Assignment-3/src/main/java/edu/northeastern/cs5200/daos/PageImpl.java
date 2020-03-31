package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Page;

public class PageImpl {
	private static PageImpl instance = null;

	private PageImpl() {}

	public static PageImpl getInstance() {
		if (instance == null)
			instance = new PageImpl();
		return instance;
	}
	
	private PreparedStatement pStatement = null;
	
	private static String CREATE_PAGE = "INSERT INTO page(id, title, description, created, updated, views, website_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static String FIND_ALL_PAGES = "SELECT * FROM page";
	private static String FIND_PAGE_BY_ID = "SELECT * FROM page WHERE page.id = ?";
	private static String FIND_PAGES_FOR_WEBSITE = "SELECT * FROM page WHERE page.website_id = ?";
	private static String UPDATE_PAGE = "UPDATE page SET page.id = ?, page.title = ?, page.description = ?, page.created = ?, page.updated = ?, "+ "page.views = ? WHERE page.id = ?";
	private static String DELETE_PAGE = "DELETE FROM page WHERE page.id = ?";
	
	public void createPageForWebsite(int websiteId, Page page) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(CREATE_PAGE);
			pStatement.setInt(1, page.getId());
			pStatement.setString(2, page.getTitle());
			pStatement.setString(3, page.getDescription());
			pStatement.setDate(4, page.getCreated());
			pStatement.setDate(5, page.getUpdated());
			pStatement.setInt(6, page.getViews());
			pStatement.setInt(7, websiteId);
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

	public Collection<Page> findAllPages() {
		ResultSet results = null;
		Collection<Page> pages = new ArrayList<Page>();
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_ALL_PAGES);
			results = pStatement.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				Page page = new Page(id, title, description, created, updated, views);
				pages.add(page);
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
		return pages;
	}

	public Page findPageById(int pageId) {
		ResultSet results = null;
		Page page = null;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_PAGE_BY_ID);
			pStatement.setInt(1, pageId);
			results = pStatement.executeQuery();
			while(results.next()){
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				page = new Page(id, title, description, created, updated, views);
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
		return page;
	}

	

	public Collection<Page> findPagesForWebsite(int websiteId) {
		ResultSet results = null;
		Collection<Page> pages = new ArrayList<Page>();
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_PAGES_FOR_WEBSITE);
			pStatement.setInt(1, websiteId);
			results = pStatement.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				Page page = new Page(id, title, description, created, updated, views);
				pages.add(page);
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
		return pages;
	}
	
	public int updatePage(int pageId, Page page) {
		int resultp = 0;

		try {
			pStatement = DBConnection.getConnection().prepareStatement(UPDATE_PAGE);
			pStatement.setInt(1, page.getId());
			pStatement.setString(2, page.getTitle());
			pStatement.setString(3, page.getDescription());
			pStatement.setDate(4, page.getCreated());
			pStatement.setDate(5, page.getUpdated());
			pStatement.setInt(6, page.getViews());
			pStatement.setInt(7, pageId);
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
		return resultp;
	}

	

	public int deletePage(int pageId) {
		int resultp = 0;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(DELETE_PAGE);
			pStatement.setInt(1, pageId);
			resultp = pStatement.executeUpdate();
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
		return resultp;
	}
}
