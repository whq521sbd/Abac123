package com.aotuo.vegetable.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class ResizeRelativeLayout extends RelativeLayout{
    public ResizeRelativeLayout(Context context) {
        super(context);
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(listener!=null){
            listener.onInputChange(oldh>h);
        }
    }
    private OnInputChangeListener listener;

    public void setOnInputChangeListener(OnInputChangeListener listener){
        this.listener=listener;
    }

    public interface OnInputChangeListener{
        void onInputChange(boolean isShow);
    }
}
