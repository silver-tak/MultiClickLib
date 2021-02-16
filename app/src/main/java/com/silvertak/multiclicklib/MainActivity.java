package com.silvertak.multiclicklib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.silvertak.multiclickdetector.MultiClickDetector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final int nClickCount = 5;
        long interval = 500;

        MultiClickDetector.detect(button, nClickCount, interval)
                .setDetector(new MultiClickDetector.Detector() {
                    @Override
                    public void onMultiClicks() {
                        Toast.makeText(MainActivity.this, nClickCount + " Times Clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}