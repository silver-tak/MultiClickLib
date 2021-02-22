package com.silvertak.multiclickdetector;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.annotation.NonNull;

import com.silvertak.multiclickdetector.detector.AllDetector;
import com.silvertak.multiclickdetector.detector.DoubleClickDetector;
import com.silvertak.multiclickdetector.detector.LongClickDetector;
import com.silvertak.multiclickdetector.detector.MultiClickDetector;

public class ClickDetector {

    View view;
    int count = -1;
    int clickCount = 0;
    long interval = 500;
    AllDetector allDetector;
    MultiClickDetector multiClickDetector;
    DoubleClickDetector doubleClickDetector;
    LongClickDetector longClickDetector;
    Handler handler;

    public static ClickDetector detect(View view, long interval)
    {
        return new ClickDetector(view, interval);
    }

    public static ClickDetector detect(View view)
    {
        return new ClickDetector(view);
    }

    public ClickDetector(View view) {
        this.view = view;
        init();
    }

    public ClickDetector(View view, long interval) {
        this.view = view;
        this.interval = interval;
        init();
    }

    private void init()
    {
        handler = new Handler(callback);

        setOnClickListener();
        setOnLongClickListener();
    }

    public void stop()
    {
        this.view.setOnClickListener(null);
        this.view.setOnLongClickListener(null);
    }

    Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            clickCount = 0;
            return true;
        }
    };

    private void setOnClickListener() {
        if(view.hasOnClickListeners())
            return;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                if (clickCount == 2) {
                    if (ClickDetector.this.doubleClickDetector != null)
                        ClickDetector.this.doubleClickDetector.onDoubleClick(view);
                }

                if (clickCount == count) {
                    if (ClickDetector.this.multiClickDetector != null)
                        ClickDetector.this.multiClickDetector.onMultiClick(view);
                } else {
                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0, interval);
                }
            }
        });
    }

    private void setOnLongClickListener() {
        if(view.hasOnLongClickListeners())
            return;

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (ClickDetector.this.allDetector != null)
                    ClickDetector.this.allDetector.onLongClick(view);
                return true;
            }
        });
    }

    public ClickDetector setAllDetector(AllDetector allDetector, int count) {
        this.allDetector = allDetector;
        this.count = count;
        setOnClickListener();
        setOnLongClickListener();
        return this;
    }

    public ClickDetector setMultiClickDetector(MultiClickDetector multiClickDetector, int count)
    {
        this.multiClickDetector = multiClickDetector;
        this.count = count;
        setOnClickListener();
        return this;
    }

    public ClickDetector setDoubleClickDetector(DoubleClickDetector doubleClickDetector) {
        this.doubleClickDetector = doubleClickDetector;
        setOnClickListener();
        return this;
    }

    public ClickDetector setLongClickDetector(LongClickDetector longClickDetector) {
        this.longClickDetector = longClickDetector;
        setOnLongClickListener();
        return this;
    }
}
