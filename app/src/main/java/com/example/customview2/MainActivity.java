package com.example.customview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ResourceHelper resourceHelper;

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    private static final int OFFSET = 120;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int mColorBackground;
    private int mColorRectangle;
    private int mColorRed;
    private int mColorYellow;
    private int mColorBlack;
    private int mColorAccent;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resourceHelper = new ResourceHelper(getResources());
        counter = 0;

        mColorBackground = ResourceHelper.getColor(R.color.color_background);
        mColorAccent = ResourceHelper.getColor(R.color.color_accent);
        mColorRectangle = ResourceHelper.getColor(R.color.color_rectangle);
        mColorRed = ResourceHelper.getColor(R.color.color_red);
        mColorYellow = ResourceHelper.getColor(R.color.color_yellow);
        mColorBlack = ResourceHelper.getColor(R.color.color_black);

//        mPaint.setColor(mColorBackground);
//        mPaintText.setColor(ResourceHelper.getColor(R.color.black));
//        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.my_image_view);
        mImageView.setOnClickListener(v -> {
            draw(v);
            v.invalidate();
        });
    }

    private void draw(View v) {
        int vWidth = mImageView.getWidth();
        int vHeight = mImageView.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;

        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
//            mCanvas.drawColor(mColorBackground);
//            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPaintText);
            mOffset += OFFSET;
        } else if (mOffset < halfWidth && mOffset < halfHeight) {
            switch (counter) {
                case 0: {
                    mPaint.setColor(mColorBlack);
                    mRect.set(100, 500, 1000, 700);
                    mCanvas.drawRect(mRect, mPaint);
                    break;
                }
                case 1: {
                    mPaint.setColor(mColorRed);
                    mRect.set(100, 700, 1000, 900);
                    mCanvas.drawRect(mRect, mPaint);
                    break;
                }
                case 2: {
                    mPaint.setColor(mColorYellow);
                    mRect.set(100, 900, 1000, 1100);
                    mCanvas.drawRect(mRect, mPaint);
                    break;
                }
            }
            counter++;
//            mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
//            mRect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);
//            mCanvas.drawRect(mRect, mPaint);
            mOffset += OFFSET;
        } else {
//            mPaint.setColor(mColorAccent - MULTIPLIER * mOffset);
//            mCanvas.drawCircle(halfWidth, halfHeight, 20, mPaint);

//            mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
//            mRect.set(400, 500, 1080, 1930);
//            mCanvas.drawRect(mRect, mPaint);
            mOffset += OFFSET;

            // triangle
//            Point a = new Point(halfWidth - 50, halfHeight - 50);
//            Point b = new Point(halfWidth + 50, halfHeight + 50);
//            Point c = new Point(halfWidth, halfHeight + 250);
//
//            Path path = new Path();
//            path.setFillType(Path.FillType.EVEN_ODD);
//            path.lineTo(a.x, a.y);
//            path.lineTo(b.x, b.y);
//            path.lineTo(c.x, c.y);
//            path.lineTo(a.x, a.y);
//            path.close();
//
//            mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
//            mCanvas.drawPath(path, mPaint);

            // text
//            String text = getString(R.string.done);
//            mPaintText.getTextBounds(text, 0, text.length(), mBounds);
//            int x = halfWidth - mBounds.centerX();
//            int y = halfHeight - mBounds.centerY();
//            mCanvas.drawText(text, x, y, mPaintText);
        }

//        v.invalidate();
    }
}