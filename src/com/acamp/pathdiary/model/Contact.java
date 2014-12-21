package com.acamp.pathdiary.model;

public class Contact { 
    
    private int seq; 
    private double lat; 
    private double lng; 
    private String si; 
    private String gu; 
    private String dong; 
    
    private String detail_addr; 
    private String reg_dt;
    private String file_name; 
    
    public Contact(){}
    
    public Contact(double lat, double lng, String detail_addr){
    	this.lat = lat; 
    	this.lng = lng; 
    	this.detail_addr = detail_addr;
    }
    
    public Contact(double lat, double lng, String detail_addr, String file_name){
    	this.lat = lat; 
    	this.lng = lng; 
    	this.detail_addr = detail_addr;
    	this.file_name = file_name; 
    }

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	
	

}
