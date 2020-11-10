package com.practice.yeonda_makerthon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView navi;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentReservation fragmentReservation;
    private FragmentSmartKey fragmentSmartKey;
    private FragmentSetting fragmentSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        navi = (BottomNavigationView)findViewById(R.id.bottom_navi);
    }
}