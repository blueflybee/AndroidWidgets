package io.github.changjiashuai.widgets.bottomsheet;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/1/25 10:09.
 */
public interface OnSheetDismissedListener {

    /**
     * Called when the presented sheet has been dismissed.
     *
     * @param bottomSheetLayout The bottom sheet which contained the presented sheet.
     */
    void onDismissed(BottomSheetLayout bottomSheetLayout);
}
