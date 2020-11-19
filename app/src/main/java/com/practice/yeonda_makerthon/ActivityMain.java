package com.practice.yeonda_makerthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityMain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private BottomNavigationView navi;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private FragmentReservation fragmentReservation;
    private FragmentSmartKey fragmentSmartKey;
    private FragmentSetting fragmentSetting;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getData();

        navi.setOnNavigationItemSelectedListener(this);
        setFrag(1);


    }

    private void getData() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = snapshot.getValue().toString();
                Log.d("aaaa","getValue : "+snapshot.getValue());
                Log.d("aaaa","getValue toString: "+snapshot.getValue().toString());
                Log.d("aaaa","getKey : "+snapshot.getKey());
                Log.d("aaaa","getChildren : "+snapshot.getChildren());
                Log.d("aaaa","getChildrenCount : "+snapshot.getChildrenCount());
                Log.d("aaaa","getRef : "+snapshot.getRef());

                String cc = "";
                try {
                    JSONObject json = new JSONObject(str);
                    cc = json.get("cc").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("aaaa","test : "+str);
                Log.d("aaaa","test : "+cc);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        navi = (BottomNavigationView)findViewById(R.id.bottom_navi);
        fragmentReservation = new FragmentReservation();
        fragmentSmartKey = new FragmentSmartKey();
        fragmentSetting = new FragmentSetting();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("spaces");


        navi.setSelectedItemId(R.id.key);//초반 픽되어있을 곳

    }

    // 프레그먼트 교체
    private void setFrag(int n)
    {
        fragmentTransaction= fragmentManager.beginTransaction();
        switch (n)
        {
            case 0:
                fragmentTransaction.replace(R.id.frameLayout,fragmentReservation);
                fragmentTransaction.commit();
                break;

            case 1:
                fragmentTransaction.replace(R.id.frameLayout,fragmentSmartKey);
                fragmentTransaction.commit();
                break;

            case 2:
                fragmentTransaction.replace(R.id.frameLayout,fragmentSetting);
                fragmentTransaction.commit();
                break;


        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.reservation:
                setFrag(0);
                break;
            case R.id.key:
                setFrag(1);
                break;
            case R.id.setting:
                setFrag(2);
                break;
        }
        return true;
    }
}