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
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

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

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private BottomNavigationView navi;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private FragmentReservation fragmentReservation;
    private FragmentSmartKey fragmentSmartKey;
    private FragmentSetting fragmentSetting;
    private LinearLayout progressBarLayout;

    FirebaseDatabase database;
    DatabaseReference myRef;

    static ArrayList<StudioTempClass> studioList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getData();
        navi.setOnNavigationItemSelectedListener(this);
        isNowLoading(true);
    }

    private void isNowLoading(boolean loading){
        //터치가 되게
        if(loading == false){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBarLayout.setVisibility(View.GONE);
            setFrag(0);
            navi.setSelectedItemId(R.id.reservation);//초반 픽되어있을 곳
        }
        //터치가 안되게
        else if(loading ==true){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBarLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getData() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studioList.clear();
                String str = snapshot.getValue().toString();
                Log.d("aaaa","getValue : "+snapshot.child("1").child("basic_info").child("address").child("add1").getValue());

                int i =1 ;
                for(DataSnapshot s : snapshot.getChildren()){
                    StudioTempClass temp = new StudioTempClass();
                    temp.setName(s.child("basic_info").child("title").getValue().toString());
                    temp.setIntroduce(s.child("intro").child("description").getValue().toString());
                    temp.setLocation(s.child("basic_info").child("address").child("add1").getValue().toString()
                            +" "+s.child("basic_info").child("address").child("add2").getValue().toString());
                    temp.setPrice(s.child("price").child("1").child("price").getValue().toString());
                    temp.setPosition(i);

                    studioList.add(temp);
                    i++;
                }
                isNowLoading(false);
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
        progressBarLayout = (LinearLayout)findViewById(R.id.login_progressbar_layout_main_act);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("spaces");



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