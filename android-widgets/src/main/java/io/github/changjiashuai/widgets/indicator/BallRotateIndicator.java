package io.github.changjiashuai.widgets.indicator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/1/26 16:53.
 */
public class BallRotateIndicator extends BaseIndicator {

    private float scale = 0.5f;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius = getWidth() / 10;
        float x = getWidth() / 2;
        float y = getHeight() / 2;

        canvas.save();
        canvas.translate(x - radius * 2 - radius, y);
        canvas.scale(scale, scale);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scale, scale);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(x + radius * 2 + radius, y);
        canvas.scale(scale, scale);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators = new ArrayList<>();
        final ValueAnimator scaleAnim = ValueAnimator.ofFloat(0.5f, 1, 0.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        scaleAnim.start();
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(getTarget(), "rotation", 0, 180, 360);
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnim.start();
        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }
}
