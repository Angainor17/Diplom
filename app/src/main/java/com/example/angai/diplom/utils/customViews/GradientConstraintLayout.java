package com.example.angai.diplom.utils.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.ArrayRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.example.angai.diplom.R;

public class GradientConstraintLayout extends ConstraintLayout {

    private Paint gradientPaint;
    private int[] currentGradient;

    public GradientConstraintLayout(Context context) {
        super(context);
    }

    public GradientConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);
        currentGradient = colors(R.array.gradientPartlyCloudy);
    }

    public GradientConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initGradient() {
        float centerX = getWidth() * 0.5f;
        Shader gradient = new LinearGradient(
                centerX, 0, centerX, getHeight(),
                currentGradient, null,
                Shader.TileMode.MIRROR);
        gradientPaint.setShader(gradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initGradient();
        canvas.drawRect(0, 0, getWidth(), getHeight(), gradientPaint);
        super.onDraw(canvas);
    }

    private int[] colors(@ArrayRes int res) {
        return getContext().getResources().getIntArray(res);
    }
}
