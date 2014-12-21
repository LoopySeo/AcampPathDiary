package com.acamp.pathdiary.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

public class BitmapUtil {    
    /**
     * Bitmap을 ratio에 맞춰서 max값 만큼 resize한다.
     *  
     * @param Bitmap 원본 
     * @param max 원하는 크기의 값
     * @return
     */
    public static Bitmap resizeBitmap(Bitmap src, int max) {
        if(src == null)
            return null;
        
        int width = src.getWidth();
        int height = src.getHeight();
        float rate = 0.0f;
        
        if (width > height) {
            rate = max / (float) width;
            height = (int) (height * rate);
            width = max;
        } else {
            rate = max / (float) height;
            width = (int) (width * rate);
            height = max;
        }

        return Bitmap.createScaledBitmap(src, width, height, true);            
    }
    
    public static Bitmap resizeBitmap(Bitmap src, int w, int h) {
        if(src == null)
            return null;
        
        return Bitmap.createScaledBitmap(src, w, h, true);            
    }
    
    /**
     * Bitmap을 ratio에 맞춰서 max값 만큼 resize한다.
     * 
     * @param src
     * @param max 
     * @param isKeep 작은 크기인 경우 유지할건지 체크..  
     * @return
     */
    public static Bitmap resize(Bitmap src, int max, boolean isKeep) {
        if(!isKeep)
            return resizeBitmap(src, max);
        
        int width = src.getWidth();
        int height = src.getHeight();
        float rate = 0.0f;
        
        if (width > height) {
            if (max > width) {
                rate = max / (float) width;
                height = (int) (height * rate);
                width = max;
            }
        } else {
            if (max > height) {
                rate = max / (float) height;
                width = (int) (width * rate);
                height = max;
            }
        }

        return Bitmap.createScaledBitmap(src, width, height, true);
    }
    
    /**
     * Bitmap 이미지를 정사각형으로 만든다.
     * 
     * @param src 원본 
     * @param max 사이즈
     * @return
     */
    public static Bitmap resizeSquare(Bitmap src, int max) {
        if(src == null)
            return null;
        
        return Bitmap.createScaledBitmap(src, max, max, true);
    }
    
    
    /**
     * Bitmap 이미지를 가운데를 기준으로 w, h 크기 만큼 crop한다. 
     * 
     * @param src 원본
     * @param w 넓이
     * @param h 높이
     * @return
     */
    public static Bitmap cropCenterBitmap(Bitmap src, int w, int h) {
        if(src == null)
            return null;
        
        int width = src.getWidth();
        int height = src.getHeight();
                
        if(width < w && height < h)
            return src;
        
        int x = 0;
        int y = 0;
        
        if(width > w)
            x = (width - w)/2;
        
        if(height > h)
            y = (height - h)/2;
        
        int cw = w; // crop width
        int ch = h; // crop height
        
        if(w > width)
            cw = width;
        
        if(h > height)
            ch = height;
        
        return Bitmap.createBitmap(src, x, y, cw, ch);
    }
    
    public static Bitmap getCircleBitmap(Bitmap bitmapimg){
    	 Bitmap output = Bitmap.createBitmap(bitmapimg.getWidth(),
                 bitmapimg.getHeight(), Config.ARGB_8888);
         Canvas canvas = new Canvas(output);

         final int color = 0xff424242;
         final Paint paint = new Paint();
         final Rect rect = new Rect(0, 0, bitmapimg.getWidth(),
                 bitmapimg.getHeight());

         paint.setAntiAlias(true);
         canvas.drawARGB(0, 0, 0, 0);
         paint.setColor(color);
         canvas.drawCircle(bitmapimg.getWidth() / 2,
                 bitmapimg.getHeight() / 2, bitmapimg.getWidth() / 2, paint);
         paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
         canvas.drawBitmap(bitmapimg, rect, rect, paint);
         return output;
    }
    
    public Bitmap combineImages(Bitmap c, Bitmap s) { // can add a 3rd parameter 'String loc' if you want to save the new image - left some code to do that at the bottom 
        Bitmap cs = null; 

        int width, height = 0; 

        if(c.getWidth() > s.getWidth()) { 
          width = c.getWidth() + s.getWidth(); 
          height = c.getHeight(); 
        } else { 
          width = s.getWidth() + s.getWidth(); 
          height = c.getHeight(); 
        } 

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

        Canvas comboImage = new Canvas(cs); 

        comboImage.drawBitmap(c, 0f, 0f, null); 
        comboImage.drawBitmap(s, c.getWidth(), 0f, null); 

        // this is an extra bit I added, just incase you want to save the new image somewhere and then return the location 
        /*String tmpImg = String.valueOf(System.currentTimeMillis()) + ".png"; 

        OutputStream os = null; 
        try { 
          os = new FileOutputStream(loc + tmpImg); 
          cs.compress(CompressFormat.PNG, 100, os); 
        } catch(IOException e) { 
          Log.e("combineImages", "problem combining images", e); 
        }*/ 

        return cs; 
      } 
}