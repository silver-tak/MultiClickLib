package com.silvertak.multiclicklib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.silvertak.multiclickdetector.ClickDetector;
import com.silvertak.multiclickdetector.detector.AllDetector;

public class MainActivity extends AppCompatActivity {

    ClickDetector clickDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final int nClickCount = 5;
        long interval = 500;

        clickDetector = ClickDetector.detect(button, interval)
                .setAllDetector(new AllDetector() {
                    @Override
                    public void onMultiClick(View view) {
                        Toast.makeText(MainActivity.this, view.toString() + " onMultiClick Call.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view) {
                        Toast.makeText(MainActivity.this, view.toString() + " onLongClick Call.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDoubleClick(View view) {
                        Toast.makeText(MainActivity.this, view.toString() + " onDoubleClick Call.", Toast.LENGTH_SHORT).show();
                    }
                }, nClickCount);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clickDetector.stop();
    }
}