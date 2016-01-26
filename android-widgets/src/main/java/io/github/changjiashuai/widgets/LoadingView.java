package io.github.changjiashuai.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import changjiashuai.utils.DensityUtils;
import io.github.changjiashuai.widgets.indicator.BallBeatIndicator;
import io.github.changjiashuai.widgets.indicator.BallClipRotateIndicator;
import io.github.changjiashuai.widgets.indicator.BallClipRotateMultipleIndicator;
import io.github.changjiashuai.widgets.indicator.BallClipRotatePulseIndicator;
import io.github.changjiashuai.widgets.indicator.BallGridBeatIndicator;
import io.github.changjiashuai.widgets.indicator.BallGridPulseIndicator;
import io.github.changjiashuai.widgets.indicator.BallPulseIndicator;
import io.github.changjiashuai.widgets.indicator.BallPulseRiseIndicator;
import io.github.changjiashuai.widgets.indicator.BallPulseSyncIndicator;
import io.github.changjiashuai.widgets.indicator.BallRotateIndicator;
import io.github.changjiashuai.widgets.indicator.BallScaleIndicator;
import io.github.changjiashuai.widgets.indicator.BallScaleMultipleIndicator;
import io.github.changjiashuai.widgets.indicator.BallScaleRippleIndicator;
import io.github.changjiashuai.widgets.indicator.BallScaleRippleMultipleIndicator;
import io.github.changjiashuai.widgets.indicator.BallSpinFadeLoaderIndicator;
import io.github.changjiashuai.widgets.indicator.BallTrianglePathIndicator;
import io.github.changjiashuai.widgets.indicator.BallZigZagDeflectIndicator;
import io.github.changjiashuai.widgets.indicator.BallZigZagIndicator;
import io.github.changjiashuai.widgets.indicator.BaseIndicator;
import io.github.changjiashuai.widgets.indicator.CubeTranslationIndicator;
import io.github.changjiashuai.widgets.indicator.LineScaleIndicator;
import io.github.changjiashuai.widgets.indicator.LineScalePartyIndicator;
import io.github.changjiashuai.widgets.indicator.LineScalePulseOutIndicator;
import io.github.changjiashuai.widgets.indicator.LineScalePulseOutRapidIndicator;
import io.github.changjiashuai.widgets.indicator.LineSpinFadeLoaderIndicator;
import io.github.changjiashuai.widgets.indicator.PacmanIndicator;
import io.github.changjiashuai.widgets.indicator.SemiCircleSpinIndicator;
import io.github.changjiashuai.widgets.indicator.SquareSpinIndicator;
import io.github.changjiashuai.widgets.indicator.TriangleSkewSpinIndicator;

/**
 *
 */
public class LoadingView extends View {

    //indicators
    public static final int BallPulse = 0;
    public static final int BallGridPulse = 1;
    public static final int BallClipRotate = 2;
    public static final int BallClipRotatePulse = 3;
    public static final int SquareSpin = 4;
    public static final int BallClipRotateMultiple = 5;
    public static final int BallPulseRise = 6;
    public static final int BallRotate = 7;
    public static final int CubeTransition = 8;
    public static final int BallZigZag = 9;
    public static final int BallZigZagDeflect = 10;
    public static final int BallTrianglePath = 11;
    public static final int BallScale = 12;
    public static final int LineScale = 13;
    public static final int LineScaleParty = 14;
    public static final int BallScaleMultiple = 15;
    public static final int BallPulseSync = 16;
    public static final int BallBeat = 17;
    public static final int LineScalePulseOut = 18;
    public static final int LineScalePulseOutRapid = 19;
    public static final int BallScaleRipple = 20;
    public static final int BallScaleRippleMultiple = 21;
    public static final int BallSpinFadeLoader = 22;
    public static final int LineSpinFadeLoader = 23;
    public static final int TriangleSkewSpin = 24;
    public static final int Pacman = 25;
    public static final int BallGridBeat = 26;
    public static final int SemiCircleSpin = 27;


    @IntDef(flag = true,
            value = {
                    BallPulse,
                    BallGridPulse,
                    BallClipRotate,
                    BallClipRotatePulse,
                    SquareSpin,
                    BallClipRotateMultiple,
                    BallPulseRise,
                    BallRotate,
                    CubeTransition,
                    BallZigZag,
                    BallZigZagDeflect,
                    BallTrianglePath,
                    BallScale,
                    LineScale,
                    LineScaleParty,
                    BallScaleMultiple,
                    BallPulseSync,
                    BallBeat,
                    LineScalePulseOut,
                    LineScalePulseOutRapid,
                    BallScaleRipple,
                    BallScaleRippleMultiple,
                    BallSpinFadeLoader,
                    LineSpinFadeLoader,
                    TriangleSkewSpin,
                    Pacman,
                    BallGridBeat,
                    SemiCircleSpin
            })
    public @interface Indicator {
    }

    //Sizes (with defaults in DP)
    public static final int DEFAULT_SIZE = 45;

    //attrs
    private Context mContext;
    private int mIndicatorId;
    private Paint mPaint;
    private BaseIndicator mBaseIndicator;

    private boolean mHasAnimation;

    public LoadingView(Context context) {
        super(context);
        mContext = context;
        init(null, 0);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LoadingView, defStyle, 0);
        mIndicatorId = a.getInt(R.styleable.LoadingView_indicator, BallPulse);
        int mIndicatorColor = a.getColor(R.styleable.LoadingView_indicator_color, Color.WHITE);
        a.recycle();
        mPaint = new Paint();
        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        applyIndicator();
    }

    private void applyIndicator() {
        switch (mIndicatorId) {
            case BallPulse:
                mBaseIndicator = new BallPulseIndicator();
                break;
            case BallGridPulse:
                mBaseIndicator = new BallGridPulseIndicator();
                break;
            case BallClipRotate:
                mBaseIndicator = new BallClipRotateIndicator();
                break;
            case BallClipRotatePulse:
                mBaseIndicator = new BallClipRotatePulseIndicator();
                break;
            case SquareSpin:
                mBaseIndicator = new SquareSpinIndicator();
                break;
            case BallClipRotateMultiple:
                mBaseIndicator = new BallClipRotateMultipleIndicator();
                break;
            case BallPulseRise:
                mBaseIndicator = new BallPulseRiseIndicator();
                break;
            case BallRotate:
                mBaseIndicator = new BallRotateIndicator();
                break;
            case CubeTransition:
                mBaseIndicator = new CubeTranslationIndicator();
                break;
            case BallZigZag:
                mBaseIndicator = new BallZigZagIndicator();
                break;
            case BallZigZagDeflect:
                mBaseIndicator = new BallZigZagDeflectIndicator();
                break;
            case BallTrianglePath:
                mBaseIndicator = new BallTrianglePathIndicator();
                break;
            case BallScale:
                mBaseIndicator = new BallScaleIndicator();
                break;
            case LineScale:
                mBaseIndicator = new LineScaleIndicator();
                break;
            case LineScaleParty:
                mBaseIndicator = new LineScalePartyIndicator();
                break;
            case BallScaleMultiple:
                mBaseIndicator = new BallScaleMultipleIndicator();
                break;
            case BallPulseSync:
                mBaseIndicator = new BallPulseSyncIndicator();
                break;
            case BallBeat:
                mBaseIndicator = new BallBeatIndicator();
                break;
            case LineScalePulseOut:
                mBaseIndicator = new LineScalePulseOutIndicator();
                break;
            case LineScalePulseOutRapid:
                mBaseIndicator = new LineScalePulseOutRapidIndicator();
                break;
            case BallScaleRipple:
                mBaseIndicator = new BallScaleRippleIndicator();
                break;
            case BallScaleRippleMultiple:
                mBaseIndicator = new BallScaleRippleMultipleIndicator();
                break;
            case BallSpinFadeLoader:
                mBaseIndicator = new BallSpinFadeLoaderIndicator();
                break;
            case LineSpinFadeLoader:
                mBaseIndicator = new LineSpinFadeLoaderIndicator();
                break;
            case TriangleSkewSpin:
                mBaseIndicator = new TriangleSkewSpinIndicator();
                break;
            case Pacman:
                mBaseIndicator = new PacmanIndicator();
                break;
            case BallGridBeat:
                mBaseIndicator = new BallGridBeatIndicator();
                break;
            case SemiCircleSpin:
                mBaseIndicator = new SemiCircleSpinIndicator();
                break;
        }
        mBaseIndicator.setTarget(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(DensityUtils.dp2px(mContext, DEFAULT_SIZE), widthMeasureSpec);
        int height = measureDimension(DensityUtils.dp2px(mContext, DEFAULT_SIZE), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBaseIndicator.draw(canvas, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasAnimation) {
            mHasAnimation = true;
            mBaseIndicator.initAnimation();
        }
    }

    @Override
    public void setVisibility(int visibility) {
        if (getVisibility() != visibility) {
            super.setVisibility(visibility);
            if (visibility == GONE || visibility == INVISIBLE) {
                mBaseIndicator.setAnimationStatus(BaseIndicator.AnimStatus.END);
            } else {
                mBaseIndicator.setAnimationStatus(BaseIndicator.AnimStatus.START);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBaseIndicator.setAnimationStatus(BaseIndicator.AnimStatus.CANCEL);
    }
}