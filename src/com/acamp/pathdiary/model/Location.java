package com.acamp.pathdiary.model;

public class Location {
	private int loc_seq;
	private double loc_lat; 
	private double loc_lng; 
	private String photo_path; 
	private String si; 
	private String gu; 
	private String dong;
	
	private String detail_addr;
	private String reg_dt;
	
	public Location(){}
	
	public Location(String photo_path){
		this.photo_path = photo_path;
	}
	
	public int getLoc_seq() {
		return loc_seq;
	}
	public void setLoc_seq(int loc_seq) {
		this.loc_seq = loc_seq;
	}
	public double getLoc_lat() {
		return loc_lat;
	}
	public void setLoc_lat(double loc_lat) {
		this.loc_lat = loc_lat;
	}
	public double getLoc_lng() {
		return loc_lng;
	}
	public void setLoc_lng(double loc_lng) {
		this.loc_lng = loc_lng;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getDetail_addr() {
		return detail_addr;
	}
	public void setDetail_addr(String detail_addr) {
		this.detail_addr = detail_addr;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	
	
	
}
