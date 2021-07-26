package com.ost.ho.model;

import java.util.List;

public class DashboardImages {
	String img;
	String status;
	String link;
	
	
	//String[] images;
	
	List<String> images;
	
	
	

	  public String getImg() { return img; } 
	  
	  public void setImg(String img) {
	  this.img = img; 
	  }
	 
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/*
	 * public String[] getImages() { return images; } public void setImages(String[]
	 * images) { this.images = images; }
	 */

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> imgList) {
		this.images = imgList;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	 
	 
	
	
	
	
	
	
	
	

}
