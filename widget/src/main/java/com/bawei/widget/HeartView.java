package com.bawei.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HeartView extends RelativeLayout {
    public HeartView(Context context) {
        super(context);
    }
    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //触摸监听
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();//事件的类型: 按下 移动 抬起
        float x = event.getX();
        float y = event.getY();
        if(action ==MotionEvent.ACTION_DOWN){
            addImageView(x,y);
        }
        return true;
    }

    private void addImageView(float x,float y) {
        //创建ImageView
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ic_action_name);//设置图片资源
        LayoutParams layoutParams = new LayoutParams(200,200);//设置宽度和高度
        layoutParams.leftMargin = (int) x-100;//设置图片的位置
        layoutParams.topMargin = (int) y-100;
        imageView.setLayoutParams(layoutParams);
        //为ImageView设置动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1, 0);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotation", 0, 30);
        rotation.setInterpolator(new CycleInterpolator(3));//周期差值器，左右晃动3次
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1, 1.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1, 1.5f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", 0, -100);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alpha)
                .with(rotation)
                .with(scaleX)
                .with(scaleY)
                .with(translationY);
        animatorSet.setDuration(700);
        animatorSet.start();
        //向容器中添加ImageView
        addView(imageView);

    }
}