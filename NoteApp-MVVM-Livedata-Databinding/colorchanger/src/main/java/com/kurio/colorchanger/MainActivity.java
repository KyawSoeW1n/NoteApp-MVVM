package com.kurio.colorchanger;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rootView;
    Button btnChangeColor;
    ColorChangerViewModel colorChangerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorChangerViewModel = ViewModelProviders.of(this).get(ColorChangerViewModel.class);


        rootView = findViewById(R.id.rootView);
        btnChangeColor = findViewById(R.id.btnChangeColor);
        colorChangerViewModel.colorId.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                rootView.setBackgroundColor(integer);
            }
        });
        btnChangeColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangeColor:
                colorChangerViewModel.colorId.setValue(generateColor());
                break;
        }
    }

    private int generateColor() {
        Random r = new Random();
        return Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }
}
