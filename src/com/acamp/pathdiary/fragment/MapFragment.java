package com.acamp.pathdiary.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.acamp.pathdiary.R;
import com.acamp.pathdiary.model.Contact;
import com.acamp.pathdiary.utils.BitmapRoundDrawable;
import com.acamp.pathdiary.utils.BitmapUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapFragment extends Fragment{
	
	private View mRootView;
	private MapView googleMapView;
	private ImageButton mBtnChange;
	private GoogleMap googleMap;
	private Activity mActivity;
	private LatLng[] markersLoc;;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_map, container, false);
		
		mActivity = getActivity();
		
		initGoogleMapView(savedInstanceState);
		
		mBtnChange = (ImageButton)mRootView.findViewById(R.id.btn_change);
		
		mBtnChange.setOnClickListener(new ImageButton.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.container, new PhotoFragment());
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.commit();
			}
		});
		
		return mRootView;
	}
	
	//draw googleMap
	private void initGoogleMapView(Bundle savedInstanceState){
		googleMapView = (MapView) mRootView.findViewById(R.id.google_map_view);
        
        googleMapView.onCreate(savedInstanceState);
        // needed to get the map to display immediately
        googleMapView.onResume(); 
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = googleMapView.getMap();
        setMultiMarker(getDummy());
        setUpMap();
	}
	
	private List<Contact> getDummy(){
		List<Contact> list = new ArrayList<Contact>();
		
		list.add(new Contact(37.497228, 126.891798, "서울특별시 구로구 구로동 영림빌딩" ));
		list.add(new Contact(37.494130, 126.894196, "서울특별시 구로구 구로4동 1201-5" ));
		list.add(new Contact(37.476591, 126.981721, "서울특별시 동작구 사당1동 588-45" ));
		list.add(new Contact(37.401723, 126.976696, "경기도 안양시 동안구 관양2동 147-6" ));
		list.add(new Contact(37.389532, 126.951038, "경기도 안양시 동안구 호계동 미래통운" ));
		list.add(new Contact(37.389329, 126.950461, "경기도 안양시 동안구 범계동 1039-1" ));
		list.add(new Contact(37.391092, 126.950571, "경기도 안양시 동안구 부흥동 1145" ));
		list.add(new Contact(37.391092, 126.950571, "경기도 안양시 동안구 부흥동 1145","1382245952711.jpg" ));
		list.add(new Contact(37.391092, 126.950571, "경기도 안양시 동안구 부흥동 1145" ));
		list.add(new Contact(37.391092, 126.950571, "경기도 안양시 동안구 부흥동 1145" ));
		list.add(new Contact(37.401710, 126.976708, "경기도 안양시 동안구 관양2동 147-6"));
		list.add(new Contact(37.451474, 127.002418, "경기도 과천시 과천동 350-8" ));
		list.add(new Contact(37.476574, 126.981678, "서울특별시 동작구 사당1동 588-45" ));
		list.add(new Contact(37.499808, 127.027219, "서울특별시 강남구 역삼동 816-3 창림빌딩", "1382413456560.jpg"));
		list.add(new Contact(37.504124, 127.024518, "서울특별시 서초구 서초4동 1303-44" ));
		list.add(new Contact(37.511036, 127.021407, "서울특별시 강남구 학동로 지하 102" ,"1388136900776.jpg"));
		list.add(new Contact(37.485221, 126.981801, "서울특별시 동작구 사당2동 147-120" ));
		list.add(new Contact(37.500252, 126.909931, "서울특별시 영등포구 신길동" ));
		list.add(new Contact(37.494151, 126.894077, "서울특별시 구로구 구로4동 1201-5" ));
		list.add(new Contact(37.497228, 126.891798, "서울특별시 구로구 구로동 영림빌딩" ,"1382704275520.jpg"));
		list.add(new Contact(37.497228, 126.891798, "서울특별시 구로구 구로동 영림빌딩" ));
		list.add(new Contact(37.497228, 126.891798, "서울특별시 구로구 구로동 영림빌딩" ));
		list.add(new Contact(37.497228, 126.891798, "서울특별시 구로구 구로동 영림빌딩" ));
		list.add(new Contact(37.497228, 126.891798, "서울특별시 구로구 구로동 영림빌딩" ));
		
		return list; 
	}
	
	private void drawPolyLine(LatLng[] locs){
		 Polyline line = googleMap.addPolyline(new PolylineOptions()
	     .add(locs)
	     .width(10)
	     .color(Color.RED));
	}
	
	private void setPosition(LatLng[] locations){
		LatLngBounds.Builder builder = new LatLngBounds.Builder();
		for (LatLng m : locations) {
		builder = builder.include(m);
		}
		LatLngBounds bounds = builder.build();
		CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, this.getResources()
		.getDisplayMetrics().widthPixels,
		this.getResources().getDisplayMetrics().heightPixels, 200);
		googleMap.moveCamera(cu);
	}
	
	
	private void setUpMap() {
		drawPolyLine(markersLoc);
		setPosition(markersLoc);
	}
	
	public void setMultiMarker(List<Contact> list){
		markersLoc = new LatLng[list.size()];
		for(int i=0; i<list.size(); i++){
			Contact item = list.get(i);
			markersLoc[i] = new LatLng(item.getLat(), item.getLng());
			if(item.getFile_name() != null){
				//new DownloadImagesTask().execute(item);
				Bitmap retBmp = null;
				try {
					File file = (new File(Environment.getExternalStorageDirectory()+"/DCIM/Facebook/"+item.getFile_name()));
					Bitmap src = BitmapFactory.decodeFile(file.getAbsolutePath());
					Bitmap bmp =  Bitmap.createScaledBitmap(src, 150, 150, true);
					retBmp = BitmapUtil.getCircleBitmap(bmp);
					googleMap.addMarker(new MarkerOptions()
					.position( new LatLng(item.getLat(), item.getLng()) )
					.title( "Title" )
					.snippet( "Description" )
					.icon(BitmapDescriptorFactory.fromBitmap(retBmp)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	/*
	public Bitmap getPhotoMarker(String url){
		View markerLayout = ((LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
		UrlImageView photoView = (UrlImageView) markerLayout.findViewById(R.id.photo_marker);
		URL photoUrl = null;
		Bitmap bmp = null; 
		try {
			photoUrl = new URL( url );
			photoView.setImageURL( photoUrl );
			bmp = createDrawableFromView(mActivity, markerLayout);
			
		} catch (MalformedURLException e) {
			bmp = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.def_marker);
		}
		bmp = Bitmap.createScaledBitmap(bmp, 100, 70, true);
		return bmp; 
	}
 /*
	// Convert a view to bitmap
	public static Bitmap createDrawableFromView(Context context, View view) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.buildDrawingCache();
		Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
 
		Canvas canvas = new Canvas(bitmap);
		view.draw(canvas);
 
		return bitmap;
	}
	
	public class DownloadImagesTask extends AsyncTask<Path, Void, Bitmap> {

	    Path path = null; 

	    @Override
	    protected Bitmap doInBackground(Path... paths) {
	        this.path = paths[0];
	        return download_Image(path.getPhoto_url());
	    }

	    @Override
	    protected void onPostExecute(Bitmap result) {
	    	if(result != null){
	    		googleMap.addMarker(new MarkerOptions()
				.position( new LatLng(path.getPath_lat(), path.getPath_lng()) )
				.title( "Title" )
				.snippet( "Description" )
				.icon(BitmapDescriptorFactory.fromBitmap(result)));
	    	}
	    	
	    }

	    private Bitmap download_Image(String url) {

	        Bitmap bmp =null;
	        try{
	            URL ulrn = new URL(url);
	            HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
	            InputStream is = con.getInputStream();
	            bmp = BitmapFactory.decodeStream(is);
	            }catch(Exception e){
	            	return null; 
	            }
	        return Bitmap.createScaledBitmap(bmp, 100, 70, true);
	    }
	}
	*/
}
