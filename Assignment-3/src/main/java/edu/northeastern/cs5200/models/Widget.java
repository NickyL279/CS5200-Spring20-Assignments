package edu.northeastern.cs5200.models;

public class Widget {
	private int id;
	private String name;
	private int width;
	private int height;
	private String css_style;
	private String css_class;
	private String text;
	private int order;
	private int size;
	private String html;
	private String src;
	private String url;
	private Boolean shareble;
	private Boolean expandable;
	
	private enum type {
		  heading,
		  youtube,
		  html,
		  image
		}
	private type dtype;
	private Page pages;
	private int pageId;
	
	public Widget(int id, String name, int width, int height, String css_class, String css_style, String text, int order, int pageId) {
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_class = css_class;
		this.css_style = css_style;
		this.text = text;
		this.order = order;
		this.setPageId(pageId);
	}
	
	public Widget() {
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_class = css_class;
		this.css_style = css_style;
		this.text = text;
		this.order = order;
		this.url = url;
		this.shareble = shareble;
		this.expandable = expandable;
		this.src = src;
		this.html = html;
		this.size = 2;
	}
	
	public Widget(String name, int width, int height, String text, int order, int pageId) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_style = "undefined";
		this.css_class = "undefined";
		this.text = text;
		this.order = order;
		this.pageId = pageId;
	}
	
	public Widget(int id, String name, int width, int height, String cssStyle, String cssClass, String text, int order) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_style = "undefined";
		this.css_class = "undefined";
		this.text = text;
		this.order = order;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getCss_style() {
		return css_style;
	}

	public void setCss_style(String css_style) {
		this.css_style = css_style;
	}

	public String getCss_class() {
		return css_class;
	}

	public void setCss_class(String css_class) {
		this.css_class = css_class;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public int getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getShareble() {
		return shareble;
	}

	public void setShareble(Boolean shareble) {
		this.shareble = shareble;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	public type getDtype() {
		return dtype;
	}

	public void setDtype(type dtype) {
		this.dtype = dtype;
	}

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
}
