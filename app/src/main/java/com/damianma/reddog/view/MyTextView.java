package com.damianma.reddog.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;

import com.damianma.reddog.R;
import com.damianma.reddog.Util.DrawableUtils;
import com.damianma.reddog.Util.UIUtils;


/**
 * Created by ZT on 2018/1/3.
 * 自定义TextView
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {

    private int normalColor;
    private int pressColor;
    private int radius;
    private Context context;

    //无选择器和圆角，只实现图文居中
    public MyTextView(Context context, boolean noSelectAndRadius) {
        super(context,null);
    }

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //获取xml里自定义的属性值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.myTextView);
        normalColor = ta.getColor(R.styleable.myTextView_normalColor, Color.RED);
        pressColor = ta.getColor(R.styleable.myTextView_pressColor, 0xffcecece);
        radius = (int) ta.getDimension(R.styleable.myTextView_radius, UIUtils.dp2px(5));
        ta.recycle();//回收
        init();
    }

    public void init(){
        this.setClickable(true);
        StateListDrawable selector = DrawableUtils.getSelector(normalColor, pressColor,radius);
        this.setBackgroundDrawable(selector);
        this.setGravity(Gravity.CENTER);
    }

    /**
     *带有图片时，使其居中
     * http://blog.csdn.net/catoop/article/details/23947345
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            //图片在文字左侧居中
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                setPadding(0, getPaddingTop(), (int)(getWidth() - bodyWidth), getPaddingBottom());//1,3参数xml设置paddingLeft和paddingRight时起用(居中显示，可设置一个即可) 2,4参数xml设置起用
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
            //图片在文字右侧居中
            Drawable drawableRight = drawables[2];
            if (drawableRight != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableRight.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                setPadding(0, getPaddingTop(), (int)(getWidth() - bodyWidth), getPaddingBottom());
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}
