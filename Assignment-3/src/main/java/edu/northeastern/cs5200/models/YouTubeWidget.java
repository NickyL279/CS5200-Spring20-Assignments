package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget{
	private String url;
	private Boolean shareble;
	private Boolean expandable;
	
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
	public YouTubeWidget(Integer id, String name, Integer width, Integer height, String css_class, String css_style,
			String text, Integer order, String url, Boolean shareble, Boolean expandable) {
		super(id, name, width, height, css_class, css_style, text, order);
		this.url = url;
		this.shareble = shareble;
		this.expandable = expandable;
	}
	public boolean isShareable() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isExpandable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
