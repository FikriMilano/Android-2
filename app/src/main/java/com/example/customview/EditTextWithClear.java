package com.example.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import java.util.Locale;

public class EditTextWithClear extends AppCompatEditText {

    private Drawable mClearButtonImage;

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        mClearButtonImage = getDrawableFromResource(R.drawable.ic_cler_opaque_24dp);
        setOnTouchListener((v, event) -> {
            Drawable drawable = getClearButtonFromCompoundDrawables();
            if (drawable != null) {
                float clearButtonWidth = getClearButtonWidth();
                boolean isClearButtonClicked = isClearButtonClicked(event.getX(), clearButtonWidth);

                if (isClearButtonClicked) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mClearButtonImage = getDrawableFromResource(R.drawable.ic_clear_black_24dp);
                        showClearButton();
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        mClearButtonImage = getDrawableFromResource(R.drawable.ic_cler_opaque_24dp);
                        getText().clear();
                        hideClearButton();
                        return true;
                    }
                } else {
                    return false;
                }
            }
            return false;
        });
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.length() != 0) showClearButton();
        if (text.length() == 0) hideClearButton();
    }

    private Drawable getDrawableFromResource(@DrawableRes int resId) {
        return ResourcesCompat.getDrawable(getResources(), resId, null);
    }

    private boolean isClearButtonClicked(float x, float clearButtonWidth) {
        String currentLocale = Locale.getDefault().getLanguage();
        if (currentLocale.equals(new Locale("ar").getLanguage())) {
            return x < clearButtonWidth;
        } else {
            return x > clearButtonWidth;
        }
    }

    private float getClearButtonWidth() {
        String currentLocale = Locale.getDefault().getLanguage();
        if (currentLocale.equals(new Locale("ar").getLanguage())) {
            return (mClearButtonImage.getIntrinsicWidth() - getPaddingStart());
        } else {
            return (getWidth() - mClearButtonImage.getIntrinsicWidth() - getPaddingEnd());
        }
    }

    private Drawable getClearButtonFromCompoundDrawables() {
        String currentLocale = Locale.getDefault().getLanguage();
        if (currentLocale.equals(new Locale("ar").getLanguage())) {
            return getCompoundDrawables()[0];
        } else {
            return getCompoundDrawables()[2];
        }
    }

    private void showClearButton() {
        String currentLocale = Locale.getDefault().getLanguage();
        if (currentLocale.equals(new Locale("ar").getLanguage())) {
            setCompoundDrawablesWithIntrinsicBounds(mClearButtonImage, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, mClearButtonImage, null);
        }
    }

    private void hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
