package com.acamp.pathdiary.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.acamp.pathdiary.R;
import com.acamp.pathdiary.adapter.PhotoListAdapter;
import com.acamp.pathdiary.model.Location;
import com.acamp.pathdiary.utils.SwipeDismissListViewTouchListener;

public class PhotoFragment extends Fragment{
	
	private View rView; 
	private ListView mListView;
	private PhotoListAdapter mAdapter; 
	private String mDate;
	private List<Location> mItems; 
	private ImageButton mBtnChButton; 
	
	public PhotoFragment(){
		
	}
	
	public PhotoFragment(String date){
		this.mDate = date;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mItems = getFilePath();
		rView = inflater.inflate(R.layout.fragment_photo, container, false);
		mListView = (ListView) rView.findViewById(R.id.lv_photo);
		mAdapter = new PhotoListAdapter(getActivity(), mItems);
		mListView.setAdapter(mAdapter);
		
		SwipeDismissListViewTouchListener touchListener =
			    new SwipeDismissListViewTouchListener(mListView,
			    new SwipeDismissListViewTouchListener.DismissCallbacks() {
			        @Override
			        public boolean canDismiss(int position) {
			            return true;
			        }
			 
			        @Override
			        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
			            for (int position : reverseSortedPositions) {
			                //mAdapter.remove(mAdapter.getItem(position));
			            	mItems.remove(position);
			            }
			            mAdapter.notifyDataSetChanged();
			        }
			    });
			mListView.setOnTouchListener(touchListener);
			mListView.setOnScrollListener(touchListener.makeScrollListener());
		
			mBtnChButton = (ImageButton)rView.findViewById(R.id.btn_change);
			mBtnChButton.bringToFront();
			mBtnChButton.setOnClickListener(new ImageButton.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.container, new MapFragment());
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					ft.commit();
				}
			});
			
		
		return rView;
	}
	
	
	
	public List<Location> getFilePath(){
		List<Location> list = new ArrayList<Location>();
		File[] files = null;
		try {
			files = (new File(Environment.getExternalStorageDirectory()+"/DCIM/Facebook/").listFiles());
			for(int i=0; i<files.length; i++){
				Log.d("debug", files[i].getPath());
				
				Location loc = new Location(files[i].getPath());
				list.add(loc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		}
		return list;
	}
	
}
