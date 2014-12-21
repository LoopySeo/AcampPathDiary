package com.acamp.pathdiary.adapter;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acamp.pathdiary.R;
import com.acamp.pathdiary.model.Location;
import com.acamp.pathdiary.utils.RecycleUtil;

public class PhotoListAdapter extends BaseAdapter{
	
	private List<Location> mItems;
	
	private List<WeakReference<View>> mRecycleList = new ArrayList<WeakReference<View>>();
	
	private Context mContext;
	private LayoutInflater mInflater;
	private ViewHolder mHolder;
	
	
	private int photoWidth; 
	private int photoHeight; 
	

	public PhotoListAdapter(Context context, List<Location> items) {
		this.mContext = context; 
		this.mItems   = items;
		this.mInflater = LayoutInflater.from(context);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		photoWidth = mContext.getResources().getDisplayMetrics().widthPixels;
		photoHeight = (mContext.getResources().getDisplayMetrics().heightPixels)/3;
		
		Log.d("debug", "width:"+photoWidth);
		Log.d("debug", "height:"+photoHeight);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		/*String si = mItems.get(position).getSi();
		String gu = mItems.get(position).getGu();
		String dong = mItems.get(position).getDong();
		String detailAddr = mItems.get(position).getDetail_addr();
		String photoPath = mItems.get(position).getPhoto_path();*/
		String photoPath = mItems.get(position).getPhoto_path();
		
		if(view == null){
			mHolder = new ViewHolder(); 
			view = mInflater.inflate(R.layout.listview_photo_item, parent, false);
			mHolder.ivPhoto = (ImageView) view.findViewById(R.id.iv_photo);
			mHolder.tvTime = (TextView) view.findViewById(R.id.tv_time);
			mHolder.tvAddr = (TextView) view.findViewById(R.id.tv_addr);
		}else{
			mHolder = (ViewHolder)view.getTag();
		}
		
		view.setTag(mHolder);
		
		try {
			mHolder.ivPhoto.setImageBitmap(getBitmap(photoPath, photoWidth, photoHeight));
		} catch (OutOfMemoryError e) {
			// TODO Auto-generated catch block
			recycleHalf();
		    System.gc();
		    return getView(position, convertView, parent);
		}
		
		mHolder.tvTime.setText("2014.11.13 am 12:00");
		mHolder.tvAddr.setText("녹사평역");
		
		return view;
	}
	
	class ViewHolder{
		ImageView ivPhoto; 
		TextView tvTime; 
		TextView tvAddr;
		Bitmap photoBitmap;
	}
	
	public Bitmap getBitmap(String path, int w, int h){
		Bitmap bitmap = null; 
		File picture = new File(path);
        if (picture.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap src = BitmapFactory.decodeFile(picture.getAbsolutePath(), options);
            if(src.getWidth() > w && src.getHeight() > h){
            	bitmap =  Bitmap.createScaledBitmap(src, w, h, true);
            }else{
            	bitmap = src;
            }
            
        }else{
        	return null; 
        }
        
        return bitmap;
	}
	
	
	public void recycleHalf() {
	      int halfSize = mRecycleList.size() / 2;
	      List<WeakReference<View>> recycleHalfList = mRecycleList.subList(0, halfSize);

	      RecycleUtil.recursiveRecycle(recycleHalfList);

	      for (int i = 0; i < halfSize; i++)
	          mRecycleList.remove(0);
	}

	public void recycle() {
		RecycleUtil.recursiveRecycle(mRecycleList);
	}
	
}
