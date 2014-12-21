package com.acamp.pathdiary.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.loopj.android.http.RequestParams;


public class Utils {
	
	/*//주소로 경로검색
	public static RequestParams searchLocationParams(String address){
		RequestParams reqParams = new RequestParams();
		reqParams.put("language", "ko");
		reqParams.put("sensor", false);
		reqParams.put("address", address);
		return reqParams; 
	}

	//경로로 주소검색
	public static RequestParams searchAddressParams(Location loc){
		RequestParams reqParams = new RequestParams();
		reqParams.put("language", "ko");
		reqParams.put("sensor", false);
		reqParams.put("latlng", String.format("%s,%s", loc.getLatitude(), loc.getLongitude()));
		
		return reqParams;
	}*/
	
	public static String getDate(String strDate){
		
		return null; 
	}
	
	public static String getToday(){
		Date today = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd");
		return transFormat.format(today);
	}
	
	public static String getNextDate(String strDate){
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date date = null; 
		try {
			date = transFormat.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null; 
		}
		
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);  
		cal.add(Calendar.DAY_OF_WEEK, 1); 
		Date nextDay = cal.getTime();  
		 
		return transFormat.format(nextDay);	  
	}
	 
	public static String getPrevDate(String strDate){
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date date = null; 
		try {
			date = transFormat.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null; 
		}
		
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);  
		cal.add(Calendar.DAY_OF_WEEK, -1); 
		Date prevDay = cal.getTime();  
		 
		return transFormat.format(prevDay); 
	}
	
	public static String getAddress(Context context, double lat, double lng){
		String address = null; 
		
		Geocoder geocoder = new Geocoder(context, Locale.getDefault());
		
		List<Address> list = null; 
		
		try {
			list = geocoder.getFromLocation(lat, lng, 1);
		} catch (IOException e) {
			Log.e("error", "get Address Fail");
			return null; 
		}
		
		if(list.size() > 0){
			Address addr = list.get(0);
			address = addr.getCountryName()+" "
					+addr.getPostalCode() + " "
					+addr.getLocality() + " "
					+addr.getThoroughfare() + " "
					+addr.getFeatureName();
		}
		
		return address;
	}
}
