package com.acamp.pathdiary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;


public class BitmapRoundDrawable extends Drawable {
	 
    private Bitmap _bitmap;
    private Paint _paint;
    private RectF _rectF;
    private int _bitmapWidth;
    private int _bitmapHeight;
     
    private int roundPx = 45;   //Round Area
     
    public BitmapRoundDrawable(Bitmap bitmap)
    {
        _bitmap = bitmap;
        _bitmapWidth = _bitmap.getWidth();
        _bitmapHeight = _bitmap.getHeight();
         
        Rect rect = new Rect(0, 0, _bitmapWidth, _bitmapHeight);
        _rectF = new RectF(rect);
        _paint = new Paint();
        _paint.setAntiAlias(true);
        _paint.setDither(true);
         
        BitmapShader shader = new BitmapShader(_bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        _paint.setShader(shader);               
    }
     
    @Override
    public void draw(Canvas canvas) {
//      canvas.drawOval(_rectF, _paint);
        canvas.drawRoundRect(_rectF, roundPx, roundPx, _paint);
    }
     
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        _rectF.set(bounds);
    }
 
    @Override
    public void setAlpha(int alpha) {
        if(_paint.getAlpha() != alpha)
        {
            _paint.setAlpha(alpha);
            invalidateSelf();
        }
         
    }
 
    @Override
    public void setColorFilter(ColorFilter cf) {
        _paint.setColorFilter(cf);
    }
 
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
 
    @Override
    public int getIntrinsicWidth() {
        return _bitmapWidth;
    }
     
    @Override
    public int getIntrinsicHeight() {
        return _bitmapHeight;
    }
     
    public void setAntiAlias(boolean aa)
    {
        _paint.setAntiAlias(aa);
        invalidateSelf();
    }
     
    @Override
    public void setFilterBitmap(boolean filter) {
        _paint.setFilterBitmap(filter);
        invalidateSelf();
    }
     
    @Override
    public void setDither(boolean dither) {
        _paint.setDither(dither);
        invalidateSelf();
    }
     
    public Bitmap getBitmap()
    {
        if(_bitmap != null)
            return _bitmap;
         
        return null;
    }   
}
