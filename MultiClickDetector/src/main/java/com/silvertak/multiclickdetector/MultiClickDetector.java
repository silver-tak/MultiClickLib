package com.silvertak.multiclickdetector;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.annotation.NonNull;

public class MultiClickDetector {

    View view;
    int count;
    int clickCount = 0;
    long interval = 0;
    Detector detector;
    Handler handler;

    public static MultiClickDetector detect(View view, int count, long interval)
    {
        return new MultiClickDetector(view, count, interval);
    }

    public MultiClickDetector(View view, int count) {
        this.view = view;
        this.count = count;

        init();
    }

    public MultiClickDetector(View view, int count, long interval) {
        this.view = view;
        this.count = count;
        this.interval = interval;

        init();
    }

    private void init()
    {
        handler = new Handler(callback);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                if (clickCount == count) {
                    if (MultiClickDetector.this.detector != null)
                        MultiClickDetector.this.detector.onMultiClicks();
                } else {
                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0, interval);
                }
            }
        });
    }

    Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            clickCount = 0;
            return true;
        }
    };

    public void setDetector(Detector detector) {
        this.detector = detector;
    }

    public interface Detector
    {
        void onMultiClicks();
    }
}
