package edu.northeastern.cs5200;

import java.sql.Date;
import java.util.Collection;

import edu.northeastern.cs5200.daos.AddressImpl;
import edu.northeastern.cs5200.daos.DeveloperImpl;
import edu.northeastern.cs5200.daos.PageImpl;
import edu.northeastern.cs5200.daos.PhoneImpl;
import edu.northeastern.cs5200.daos.RoleImpl;
import edu.northeastern.cs5200.daos.UserImpl;
import edu.northeastern.cs5200.daos.WebsiteImpl;
import edu.northeastern.cs5200.daos.WidgetImpl;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class hw_jdbc_liu_zhaocheng {
	public static void main(String[] args) {
		createPerson();
		createWebsites();
		createWidgets();
		createPages();
		implUpdates();
		implDelete();
	}

	public static void createPerson() {
		/*--- Insert Developer ---*/
		Developer alice = new Developer(12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null, "4321rewq");
		DeveloperImpl.getInstance().createDeveloper(alice);
		//System.out.println("alice Created");
        Address alicea1 = new Address("123 Adam St.","","Alton","","01234",true,12);
        Address alicea2 = new Address("234 Birch St","","Boston","","02345",false,12);
        AddressImpl.getInstance().addAddress(12, alicea1);
        AddressImpl.getInstance().addAddress(12, alicea2);
        //System.out.println("alice address Created");
        
		Developer bob = new Developer(23, "Bod", "Marley", "bob", "bob", "bob@marley.com", null, "5432trew");
		DeveloperImpl.getInstance().createDeveloper(bob);
		//System.out.println("bob Created");

		Developer charlie = new Developer(34, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null,
				"6543ytre");
		DeveloperImpl.getInstance().createDeveloper(charlie);
		//System.out.println("charlie Created");

		/*--- Insert User ---*/
		User dan = new User(45, "Dan", "Martin", "charlie", "charlie", "chuch@garcia.com", null);
		UserImpl.getInstance().createUser(dan);
		//System.out.println("dan Created");

		User ed = new User(56, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null);
		UserImpl.getInstance().createUser(ed);
		//System.out.println("Ed Created");
	}

	public static void createWebsites() {
		/*--- Insert Websites ---*/
		String createddate = "2020-02-18";
		Date cD = Date.valueOf(createddate);
		Website facebook = new Website(123, "Facebook", "an online social media and social networking service", cD, cD,
				1234234, -1);
		WebsiteImpl.getInstance().createWebsiteForDeveloper(-1, facebook);
		//System.out.println("Facebook Created");
		RoleImpl.getInstance().assignWebsiteRole(12, 123, "owner");
		RoleImpl.getInstance().assignWebsiteRole(23, 123, "editor");
		RoleImpl.getInstance().assignWebsiteRole(34, 123, "admin");
		//System.out.println("Facebook Role Created");

		Website twitter = new Website(234, "Twitter", "an online news and social networking service", cD, cD, 4321543,
				-1);
		WebsiteImpl.getInstance().createWebsiteForDeveloper(-1, twitter);
		//System.out.println("Twitter Created");
		RoleImpl.getInstance().assignWebsiteRole(23, 234, "owner");
		RoleImpl.getInstance().assignWebsiteRole(34, 234, "editor");
		RoleImpl.getInstance().assignWebsiteRole(12, 234, "admin");
		//System.out.println("Twitter Role Created");

		Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia", cD, cD, 3456654, -1);
		WebsiteImpl.getInstance().createWebsiteForDeveloper(-1, wikipedia);
		//System.out.println("Wikipedia Created");
		RoleImpl.getInstance().assignWebsiteRole(34, 345, "owner");
		RoleImpl.getInstance().assignWebsiteRole(12, 345, "editor");
		RoleImpl.getInstance().assignWebsiteRole(23, 345, "admin");
		//System.out.println("Wikipedia Role Created");

		Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", cD, cD,
				6543345, -1);
		WebsiteImpl.getInstance().createWebsiteForDeveloper(-1, cnn);
		//System.out.println("CNN Created");
		RoleImpl.getInstance().assignWebsiteRole(12, 456, "owner");
		RoleImpl.getInstance().assignWebsiteRole(23, 456, "editor");
		RoleImpl.getInstance().assignWebsiteRole(34, 456, "admin");
		//System.out.println("CNN Role Created");

		Website cnet = new Website(567, "CNET",
				"an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",
				cD, cD, 5433455, -1);
		WebsiteImpl.getInstance().createWebsiteForDeveloper(-1, cnet);
		//System.out.println("CNET Created");
		RoleImpl.getInstance().assignWebsiteRole(23, 567, "owner");
		RoleImpl.getInstance().assignWebsiteRole(34, 567, "editor");
		RoleImpl.getInstance().assignWebsiteRole(12, 567, "admin");
		//System.out.println("CNET Role Created");

		Website gizmodo = new Website(678, "Gizmodo",
				"a design, technology, science and science fiction website that also writes articles on politics", cD,
				cD, 4322345, -1);
		WebsiteImpl.getInstance().createWebsiteForDeveloper(-1, gizmodo);
		//System.out.println("Gizmodo Created");
		RoleImpl.getInstance().assignWebsiteRole(34, 678, "owner");
		RoleImpl.getInstance().assignWebsiteRole(12, 678, "editor");
		RoleImpl.getInstance().assignWebsiteRole(23, 678, "admin");
		//System.out.println("Gizmodo Role Created");
	}

	public static void createWidgets() {
		/*--- Insert Widgets ---*/
		String pDate = "2020-02-18";
		Date pD = Date.valueOf(pDate);

		Page home = new Page(123, "Home", "Landing page", pD, pD, 123434);
		PageImpl.getInstance().createPageForWebsite(567, home);
		//System.out.println("Home Created");
		RoleImpl.getInstance().assignPageRole(12, 123, "editor");
		RoleImpl.getInstance().assignPageRole(23, 123, "reviewer");
		RoleImpl.getInstance().assignPageRole(34, 123, "writer");
		//System.out.println("Home Role Created");

		Page about = new Page(234, "About", "Website description", pD, pD, 234545);
		PageImpl.getInstance().createPageForWebsite(678, about);
		//System.out.println("About Created");
		RoleImpl.getInstance().assignPageRole(23, 234, "editor");
		RoleImpl.getInstance().assignPageRole(34, 234, "reviewer");
		RoleImpl.getInstance().assignPageRole(12, 234, "writer");
		//System.out.println("About Role Created");

		Page contact = new Page(345, "Contact", "Addresses, phones, and contact info", pD, pD, 345656);
		PageImpl.getInstance().createPageForWebsite(345, contact);
		//System.out.println("Contact Created");
		RoleImpl.getInstance().assignPageRole(34, 345, "editor");
		RoleImpl.getInstance().assignPageRole(12, 345, "reviewer");
		RoleImpl.getInstance().assignPageRole(23, 345, "writer");
		//System.out.println("Contact Role Created");

		Page preferences = new Page(456, "Preferences", "Where users can configure their preferences", pD, pD, 456776);
		PageImpl.getInstance().createPageForWebsite(456, preferences);
		//System.out.println("Preferences Created");
		RoleImpl.getInstance().assignPageRole(12, 456, "editor");
		RoleImpl.getInstance().assignPageRole(23, 456, "reviewer");
		RoleImpl.getInstance().assignPageRole(34, 456, "writer");
		//System.out.println("Preferences Role Created");

		Page profile = new Page(567, "Profile", "Users can configure their personal information", pD, pD, 567878);
		PageImpl.getInstance().createPageForWebsite(567, profile);
		//System.out.println("Profile Created");
		RoleImpl.getInstance().assignPageRole(23, 567, "editor");
		RoleImpl.getInstance().assignPageRole(34, 567, "reviewer");
		RoleImpl.getInstance().assignPageRole(12, 567, "writer");
		//System.out.println("Profile Role Created");
	}

	public static void createPages() {
		/*--- Insert Pages ---*/
		Widget head123 = new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome", 0, 0);
		WidgetImpl.getInstance().createWidgetForPage(123, head123);
		//System.out.println("head Created");

		Widget post234 = new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0, null);
		WidgetImpl.getInstance().createWidgetForPage(234, post234);
		//System.out.println("post234 Created");

		Widget head345 = new HeadingWidget(3, "head345", 0, 0, null, null, "Hi", 1, 0);
		WidgetImpl.getInstance().createWidgetForPage(345, head345);
		//System.out.println("head345 Created");

		Widget intro456 = new HtmlWidget(4, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2, null);
		WidgetImpl.getInstance().createWidgetForPage(345, intro456);
		//System.out.println("intro456 Created");

		Widget image345 = new ImageWidget(5, "image345", 50, 100, null, null, null, 3, "/img/567.png");
		WidgetImpl.getInstance().createWidgetForPage(345, image345);
		//System.out.println("image345 Created");

		Widget video456 = new YouTubeWidget(6, "video456", 400, 300, null, null, null, 0,
				"https://youtu.be/h67VX51QXiQ", false, false);
		WidgetImpl.getInstance().createWidgetForPage(456, video456);
		//System.out.println("video456 Created");
	}

	public static void implUpdates() {
		/*--- Update Phone ---*/
		PhoneImpl phoneImpl = PhoneImpl.getInstance();
		int userId = DeveloperImpl.getInstance().findDeveloperByUsername("charlie").getId();
		String phoneNumber = "333-444-5555";
		if (phoneImpl.updatePrimaryPhone(userId, phoneNumber) == 0)
			phoneImpl.createPrimaryPhone(userId, phoneNumber);
		//System.out.println("phone updated");

		/*--- Update Widget ---*/
		int widgetId;
		WidgetImpl widgetImpl = WidgetImpl.getInstance();
		Widget widget;
		widgetId = 3;
		widget = widgetImpl.findWidgetById(widgetId);
		widget.setOrder(3);
		widgetImpl.updateWidget(widgetId, widget);

		widgetId = 4;
		widget = widgetImpl.findWidgetById(4);
		widget.setOrder(1);
		widgetImpl.updateWidget(widgetId, widget);

		widgetId = 5;
		widget = widgetImpl.findWidgetById(5);
		widget.setOrder(2);
		widgetImpl.updateWidget(widgetId, widget);

		/*--- Update Widget ---*/
		int websiteId;
		PageImpl pageImpl = PageImpl.getInstance();
		RoleImpl roleImpl = RoleImpl.getInstance();
		WebsiteImpl websiteImpl = WebsiteImpl.getInstance();
		DeveloperImpl developerImpl = DeveloperImpl.getInstance();

		websiteId = 567;
		websiteImpl.findWebsiteById(websiteId);
		Collection<Page> pages = pageImpl.findPagesForWebsite(websiteId);

		for (Page page : pages) {
			int pageId = page.getId();
			page.setTitle("CNET-" + page.getTitle());
			pageImpl.updatePage(pageId, page);
		}

		/*--- Update Widget ---*/
		int pageId = 123;
		Developer charlieU = developerImpl.findDeveloperByUsername("charlie");
		int charId = charlieU.getId();
		//System.out.println(charId);
		Developer bobU = developerImpl.findDeveloperByUsername("bob");
		int bobId = bobU.getId();
		//System.out.println(bobId);

		roleImpl.deletePageRole(charId, pageId, "WRITER");
		roleImpl.deletePageRole(bobId, pageId, "REVIEWER");
		roleImpl.assignPageRole(charId, pageId, "REVIEWER");
		roleImpl.assignPageRole(bobId, pageId, "WRITER");
	}

	public static void implDelete() {
		/*--- Delete developer ---*/
		int aliceId = DeveloperImpl.getInstance().findDeveloperByUsername("alice").getId();
		//System.out.println(aliceId);
		DeveloperImpl.getInstance().deletePrimaryAddress(aliceId);
		//System.out.println("Address deleted");
		
		/*--- Delete widget ---*/
		int pageId;
		PageImpl pageImpl = PageImpl.getInstance();
		Page page;
		pageId = 345;
		page = pageImpl.findPageById(pageId);
		//System.out.println(page);
		if (page != null) {
			//System.out.println("enter 1");
			WidgetImpl.getInstance().deleteWidget(3);
		}
		
//		if (page != null) {
//			System.out.println("enter 1");
//			Collection<Widget> widgets = page.getWidgets();
//			//if (widgets != null /*&& !widgets.isEmpty()*/) {
//				System.out.println("enter 2");
//				int max = 0;
//				Widget maxWidget = new Widget();
//				for (Widget w : widgets) {
//					if (w.getOrder() > max) {
//						max = w.getOrder();
//						maxWidget = w;
//					}
//				}
//				System.out.println(max);
//				WidgetImpl.getInstance().deleteWidget(maxWidget.getId());
//			//}
//		}
		
		/*--- Delete website ---*/
		int websiteId;
		websiteId = 345;
		Website website;
		WebsiteImpl websiteImpl = WebsiteImpl.getInstance();
		website = websiteImpl.findWebsiteById(websiteId);
		if (website != null) {
			Collection<Page> pages = website.getPages();
			if (pages != null && !pages.isEmpty()) {
				java.sql.Date date = java.sql.Date.valueOf("2017-01-01");
				Page maxPage = new Page();
				for (Page pg : pages) {
					java.sql.Date time = pg.getUpdated();
					if (date.compareTo(time) < 0) {
						date = time;
						maxPage = pg;
					}
				}
				pages.remove(maxPage);
			} else {}
			website.setPages(pages);
			websiteImpl.updateWebsite(websiteId, website);
		} else {}

		/*--- Delete address ---*/
		websiteId = 567;
		website = websiteImpl.findWebsiteById(websiteId);
		if (website != null) {
			websiteImpl.deleteWebsite(websiteId);
		} else {}
	}
}
