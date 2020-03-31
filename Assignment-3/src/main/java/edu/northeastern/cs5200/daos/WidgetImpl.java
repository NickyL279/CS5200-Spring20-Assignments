package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetImpl {
	private static WidgetImpl instance = null;

	private WidgetImpl() {
	}

	public static WidgetImpl getInstance() {
		if (instance == null) {
			instance = new WidgetImpl();
		}
		return instance;
	}

	private PreparedStatement pStatement = null;

    private final String INSERT_WIDGET = "INSERT INTO widget (id, name, width, height, css_class, css_style, text, `order`, page_id) VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?)";
//	private static String CREATE_HTML_WIDGET =
//			"INSERT INTO widget (id, `name`, width, height, css_class, css_style, `text`, `order`, `page_id`, `dtype`, html)" 
//				+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		private static String CREATE_IMAGE_WIDGET =
//			"INSERT INTO widget (id, `name`, width, height, css_class, css_style, `text`, `order`, `page_id`, `dtype`, src)" 
//				+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		private static String CREATE_YOUTUBE_WIDGET =
//			"INSERT INTO widget (id, `name`, width, height, css_class, css_style, `text`, `order`, `page_id`, `dtype`, url, shareble, expandable)" 
//				+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
	private final String FIND_ALL_WIDGETS = "SELECT * FROM widget";
	private final String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id=?";
	private final String FIND_WIDGETS_FOR_PAGE = "SELECT * FROM widget WHERE page_id=?";
	
	private final String UPDATE_WIDGET = "UPDATE widget SET name=?, width=?, height=?, css_class=?, css_style=?, text=?, `order`=?, page_id=? WHERE id=?";
	private final String DELETE_WIDGET = "DELETE FROM widget WHERE id=?";

	public void createWidgetForPage(int pageId, Widget widget) {
		try {
			
			pStatement = DBConnection.getConnection().prepareStatement(INSERT_WIDGET);
			pStatement.setInt(1, widget.getId());
			pStatement.setString(2, widget.getName());
			pStatement.setInt(3, widget.getWidth());
			pStatement.setInt(4, widget.getHeight());
			pStatement.setString(5, widget.getCss_class());
			pStatement.setString(6, widget.getCss_style());
			pStatement.setString(7, widget.getText());
			pStatement.setInt(8, widget.getOrder());
			pStatement.setInt(9, pageId);
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

	public Collection<Widget> findAllWidgets() {
		ArrayList<Widget> widgets = new ArrayList<>();
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_ALL_WIDGETS);
			ResultSet res = pStatement.executeQuery();
			while (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				Integer width = (Integer) res.getObject("width");
				Integer height = (Integer) res.getObject("height");
				String cssClass = res.getString("css_class");
				String cssStyle = res.getString("css_style");
				String text = res.getString("text");
				Integer order = res.getInt("order");
				int pageId = res.getInt("page_id");
				Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
				// add page
				PageImpl dao = PageImpl.getInstance();
				Page page = dao.findPageById(pageId);
				widget.setPages(page);
				widgets.add(widget);
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
		return widgets;
	}

	public Widget findWidgetById(int widgetId) {
		Widget wid = null;
		try {
			pStatement = DBConnection.getConnection().prepareStatement(FIND_WIDGET_BY_ID);
			pStatement.setInt(1, widgetId);
			ResultSet res = pStatement.executeQuery();
			while (res.next()) {
				String name = res.getString("name");
				Integer width = (Integer) res.getObject("width");
				Integer height = (Integer) res.getObject("height");
				String cssClass = res.getString("css_class");
				String cssStyle = res.getString("css_style");
				String text = res.getString("text");
				Integer order = res.getInt("order");
				int id = res.getInt("id");
				int pageId = res.getInt("page_id");
				wid = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
				// add page
				PageImpl dao = PageImpl.getInstance();
				Page page = dao.findPageById(pageId);
				wid.setPages(page);
				return wid;
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
		return null;
	}

//	public Collection<Widget> findWidgetsForPage(int pageId) {
//		Collection<Widget> widgets = new ArrayList<>();
//		try {
//			pStatement = DBConnection.getConnection().prepareStatement(FIND_WIDGETS_FOR_PAGE);
//			pStatement.setInt(1, pageId);
//			ResultSet res = pStatement.executeQuery();
//			while (res.next()) {
//				String name = res.getString("name");
//				Integer width = (Integer) res.getObject("width");
//				Integer height = (Integer) res.getObject("height");
//				String cssClass = res.getString("css_class");
//				String cssStyle = res.getString("css_style");
//				String text = res.getString("text");
//				Integer order = res.getInt("order");
//				int id = res.getInt("id");
//				Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
//				PageImpl dao = PageImpl.getInstance();
//				Page page = dao.findPageById(pageId);
//				widget.setPages(page);
//				widgets.add(widget);
//			}
//
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pStatement.close();
//				DBConnection.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return widgets;
//	}
	
//	public Collection<Widget> findWidgetsForPage(int pageId){
//		ResultSet results = null;
//		Collection<Widget> widgets = new ArrayList<Widget>();
//		try {
//				pStatement = DBConnection.getConnection().prepareStatement(FIND_WIDGETS_FOR_PAGE);
//				pStatement.setInt(1, pageId);
//				results = pStatement.executeQuery();
//
//				while(results.next()) {
//					Integer id = results.getInt("id");
//					String name = results.getString("name");
//					Integer width = results.getInt("width");
//					Integer height = results.getInt("height");
//					String css_class = results.getString("css_class");
//					String css_style = results.getString("css_style");
//					String text = results.getString("text");
//					Integer order = results.getInt("order");
//					String type = results.getString("type");
//					Widget widget = null;
//
//					try {
//						//connection = DatabaseConnection.getInstance().getConnection();
//						
//						if (type.equals("heading")){
//								Integer size = results.getInt("size");
//								widget = new HeadingWidget(id, name, width, height, css_class, css_style, text, order, size);
//
//						}else if(type.equals("html")){
//								String html = results.getString("html");
//								widget = new HtmlWidget(id, name, width, height, css_class, css_style, text, order, html);
//						}else if(type.equals("youtube")){
//								String url = results.getString("url");
//								Boolean shareble = results.getBoolean("shareble");
//								Boolean expandable = results.getBoolean("expandable");
//								widget = new YouTubeWidget(id, name, width, height, css_class, css_style, text, order, url, shareble, expandable);
//						}else if(type.equals("image")){
//								String src = results.getString("src");
//								widget = new ImageWidget(id, name, width, height, css_class, css_style, text, order, src);
//						}
//					}catch(SQLException e) {
//							e.printStackTrace();
//						}
//					widgets.add(widget);
//				}
//
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pStatement.close();
//				DBConnection.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return widgets;
//	}
	
	public int updateWidget(int widgetId, Widget widget) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(UPDATE_WIDGET);
			pStatement.setString(1, widget.getName());
			pStatement.setInt(2, widget.getWidth());
			pStatement.setInt(3, widget.getHeight());
			pStatement.setString(4, widget.getCss_class());
			pStatement.setString(5, widget.getCss_style());
			pStatement.setString(6, widget.getText());
			pStatement.setInt(7, widget.getOrder());
			pStatement.setInt(8, widget.getPages().getId());
			pStatement.setInt(9, widgetId);
			int res = pStatement.executeUpdate();
			return res;
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
		return -1;
	}

	public int deleteWidget(int widgetId) {
		try {
			pStatement = DBConnection.getConnection().prepareStatement(DELETE_WIDGET);

			pStatement.setInt(1, widgetId);
			int res = pStatement.executeUpdate();
			return res;
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
		return -1;
	}
}
