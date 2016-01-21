package io.github.changjiashuai.widgets.indicator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/1/21 23:21.
 *
 * 多个球剪辑旋转
 */
public class BallClipRotateMultipleIndicator extends BaseIndicator {
    private float scaleFloat = 1, degrees;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        float circleSpacing = 12;
        float x = getWidth()/2;
        float y = getHeight()/2;
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);

        //draw two big arc
        float[] bStartAngles = {135, -45};
        for (int i=0;i<2;i++){
            RectF rectF = new RectF(-x+circleSpacing, -y+circleSpacing, x-circleSpacing, y-circleSpacing);
            canvas.drawArc(rectF, bStartAngles[i], 90, false, paint);
        }

        canvas.restore();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(-degrees);

        //draw two small arc
        float[] sStartAngles = {225, 45};
        for (int i=0;i<2;i++){
            RectF rectF = new RectF(-x/1.8f+circleSpacing, -y/1.8f+circleSpacing, x/1.8f-circleSpacing, y/1.8f-circleSpacing);
            canvas.drawArc(rectF,sStartAngles[i],90, false, paint);
        }
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators = new ArrayList<>();
        final ValueAnimator scaleAnim = ValueAnimator.ofFloat(1,0.6f,1);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloat = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        scaleAnim.start();

        ValueAnimator rotationAnim = ValueAnimator.ofFloat(0,180,360);
        rotationAnim.setDuration(1000);
        rotationAnim.setRepeatCount(-1);
        rotationAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        rotationAnim.start();
        animators.add(scaleAnim);
        animators.add(rotationAnim);
        return animators;
    }
}
