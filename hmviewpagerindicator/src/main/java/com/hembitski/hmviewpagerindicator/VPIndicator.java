package com.hembitski.hmviewpagerindicator;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class VPIndicator extends View implements ViewPager.OnPageChangeListener {

    private Context context;
    private Bitmap emptyBitmap, indicatorBitmap;
    private ViewPager viewPager;
    private int pageCount;
    private int emptyX, indicatorX;

    public VPIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setParams(ViewPager viewPager) {
        this.viewPager = viewPager;
        pageCount = viewPager.getAdapter().getCount();
        emptyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty);
        indicatorBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.indicator);
    }

    public void setParams(ViewPager viewPager, int resIdBackground, int resIdIndicator) {
        this.viewPager = viewPager;
        pageCount = viewPager.getAdapter().getCount();
        emptyBitmap = BitmapFactory.decodeResource(context.getResources(), resIdBackground);
        indicatorBitmap = BitmapFactory.decodeResource(context.getResources(), resIdIndicator);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        emptyBitmap = bitmapSizeCorrection(emptyBitmap, h);
        indicatorBitmap = bitmapSizeCorrection(indicatorBitmap, h);

        setImagesCoordinate(w);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = emptyBitmap.getWidth() * pageCount;
        int desiredHeight = emptyBitmap.getHeight();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }
        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x = emptyX;
        for (int i = 0; i < pageCount; i++) {
            canvas.drawBitmap(emptyBitmap, x, 0, null);
            x += emptyBitmap.getWidth();
        }
        canvas.drawBitmap(indicatorBitmap, indicatorX, 0, null);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        switch (visibility) {
            case View.VISIBLE:
                viewPager.addOnPageChangeListener(this);
                break;
            default:
                viewPager.removeOnPageChangeListener(this);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int posWidth = indicatorBitmap.getWidth();
        indicatorX = emptyX + position * posWidth + (int) (posWidth * positionOffset);
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private static Bitmap bitmapSizeCorrection(Bitmap bitmap, int dstHeight) {
        float scaleFactor = (float) dstHeight / (float) bitmap.getHeight();
        int height = (int) (scaleFactor * (float) bitmap.getHeight());
        int width = (int) (scaleFactor * (float) bitmap.getWidth());
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    private void setImagesCoordinate(int w) {
        int width = emptyBitmap.getWidth() * pageCount;
        emptyX = w / 2 - width / 2;
        indicatorX = emptyX + viewPager.getCurrentItem() * indicatorBitmap.getWidth();
    }
}
