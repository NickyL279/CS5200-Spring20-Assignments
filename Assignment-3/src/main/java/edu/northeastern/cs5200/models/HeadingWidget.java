package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget {
	private int size;
	
	public HeadingWidget(int id, String name, int width, int height, String css_class, String css_style, String text,
			int order, int size) {
		super(id, name, width, height, css_class, css_style, text, order);
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
