package com.acamp.pathdiary;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acamp.pathdiary.fragment.MapFragment;
import com.acamp.pathdiary.fragment.PhotoFragment;
import com.acamp.pathdiary.utils.Utils;

public class MainActivity extends Activity {
	
	private final int CONTAINER_ID = R.id.container;
	
	private TextView mTvDate; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setCustomActionBar();
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(CONTAINER_ID, new MapFragment()).commit();
		}
		
	}
	
	private void changeFragment(Fragment fragment){
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(CONTAINER_ID, fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
	}
	
	private void setCustomActionBar(){
		mTvDate			= null; 
		ImageButton btnCalendar = null; 
		ImageButton btnSettings = null; 
		ImageButton btnLeft 	= null; 
		ImageButton btnRight	= null; 
		
		final ActionBar actionBar = getActionBar();
		actionBar.getHeight();
		
		View cView = getLayoutInflater().inflate(R.layout.layout_menu, null);
		mTvDate		= (TextView) cView.findViewById(R.id.tv_date);
		btnCalendar = (ImageButton)cView.findViewById(R.id.btn_calendar);
		btnSettings = (ImageButton)cView.findViewById(R.id.btn_settings);
		btnLeft		= (ImageButton)cView.findViewById(R.id.btn_left);
		btnRight	= (ImageButton)cView.findViewById(R.id.btn_right);
		
		mTvDate.setText(Common.selectedDate);
		
		btnCalendar.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "onclick calendar", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnSettings.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "onclick settings", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnLeft.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Common.setPrevDate();
				mTvDate.setText(Common.selectedDate);
			}
		});
		
		btnRight.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Common.setNextDate();
				mTvDate.setText(Common.selectedDate);
			}
		});
		
		
		
		
        actionBar.setCustomView(cView);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setIcon( new ColorDrawable(getResources().getColor(android.R.color.transparent)));
	}

}
