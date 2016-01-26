package io.github.changjiashuai.widgets.bottomsheet;

import android.view.View;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/1/22 16:01.
 */
public abstract class BaseViewTransformer implements ViewTransformer {
    public static final float MAX_DIM_ALPHA = 0.7f;

    @Override
    public float getDimAlpha(float translation, float maxTranslation, float peekedTranslation, BottomSheetLayout parent, View view) {
        float progress = translation / maxTranslation;
        return progress * MAX_DIM_ALPHA;
    }
}
