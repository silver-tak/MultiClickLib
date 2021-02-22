package com.silvertak.multiclickdetector.detector;

import android.view.View;

public interface AllDetector
{
    void onMultiClick(View view);
    void onLongClick(View view);
    void onDoubleClick(View view);
}
