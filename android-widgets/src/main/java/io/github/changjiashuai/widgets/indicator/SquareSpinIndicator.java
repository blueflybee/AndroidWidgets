package io.github.changjiashuai.widgets.indicator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/1/21 22:55.
 *
 * 正方形自旋
 */
public class SquareSpinIndicator extends BaseIndicator {

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(new RectF(getWidth() / 5, getHeight() / 5, getWidth() * 4 / 5, getHeight() * 4 / 5), paint);
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators = new ArrayList<>();
        PropertyValuesHolder rotationX = PropertyValuesHolder.ofFloat("rotationX", 0, 180, 180, 0, 0);
        PropertyValuesHolder rotationY = PropertyValuesHolder.ofFloat("rotationY", 0, 0, 180, 180, 0);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(getTarget(), rotationX, rotationY);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.setDuration(2500);
        animator.start();
        animators.add(animator);
        return animators;
    }
}
