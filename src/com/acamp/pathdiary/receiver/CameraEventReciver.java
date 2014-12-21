package com.acamp.pathdiary.receiver;

import java.util.List;

import com.acamp.pathdiary.utils.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CameraEventReciver extends BroadcastReceiver{
	
	private Context mContext; 
	
	private Location mLoc;
	private LocationManager lm;
	private String provider; 
	private String imgUri; 
	
	@Override
	public void onReceive(Context context, Intent intent) {
		this.mContext = context;
		

		
		Uri uri = intent.getData();
		imgUri = uri.toString();
		
		lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria c = new Criteria();
		provider = lm.getBestProvider(c, true);
		
		if(provider == null || !lm.isProviderEnabled(provider)){
			List<String> list = lm.getAllProviders();
			for(int i=0; i<list.size(); i++){
				String temp = list.get(i);
				if(lm.isProviderEnabled(temp)){
					provider = temp; 
					break; 
				}
			}
		}
		
		Log.d("debug", "provider:"+provider);
		
		if(provider == null || provider.equals("passive")){
			Toast.makeText(context, "GPS를 활성화 시키면 PathDiary를 이용하실 수 있습니다.", Toast.LENGTH_SHORT).show();
		}else{
			registerLocationUpdates();
		}
		
		
	}
	
	private void registerLocationUpdates() {
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 1, mLocationListener);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000, 1, mLocationListener);
	}
	
	private final LocationListener mLocationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		
		@Override
		public void onProviderEnabled(String provider) {
		}
		
		@Override
		public void onProviderDisabled(String provider) {
		}
		
		@Override
		public void onLocationChanged(Location location) {
			double longitude = location.getLongitude();    //경도
			double latitude = location.getLatitude();         //위도
			float accuracy = location.getAccuracy();        //신뢰도
			
			Log.d("debug", "lat:"+latitude +"/ lng:"+longitude);
			
			Log.d("debug", Utils.getAddress(mContext, latitude, longitude));
			
			Toast.makeText(mContext, "lat:"+latitude +"/ lng:"+longitude, Toast.LENGTH_SHORT).show();
			
			
			lm.removeUpdates(this);
		}
	};
	
}
