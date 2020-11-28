package com.practice.yeonda_makerthon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentSmartKey extends Fragment {
    View view;
    private TextView remainTime_h;
    private TextView remainTime_m;
    private TextView endTime;
    private ProgressBar progressBar;
    private TextView studioName, studioLocation;
    private Button returnButton;
    private CheckBox nfcCheckBox;
    private LinearLayout openButton, closeButton;

    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference getRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_smart_key, container, false);

        initView();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                endTime.setText(snapshot.child("current_key_holder").child("1").child("end_date").getValue().toString());
                long now = System.currentTimeMillis();
                // 현재시간을 date 변수에 저장한다.
                Date date = new Date(now);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow_h = new SimpleDateFormat("HH");
                // nowDate 변수에 값을 저장한다.
                String formatDate_h = sdfNow_h.format(date);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow_m = new SimpleDateFormat("mm");
                // nowDate 변수에 값을 저장한다.
                String formatDate_m = sdfNow_m.format(date);

                if(Integer.valueOf(formatDate_h)==11)
                    remainTime_h.setText("0시간");
                else
                    remainTime_h.setText("1시간");

                int remain_m = 60 - Integer.valueOf(formatDate_m);
                remainTime_m.setText(String.valueOf(remain_m));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("opening").setValue(false);
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("opening").setValue(true);
            }
        });

        return  view;
    }

    private void initView() {
        remainTime_h = (TextView)view.findViewById(R.id.remain_time_hour_smartKey);
        remainTime_m = (TextView)view.findViewById(R.id.remain_time_min_smartKey);
        endTime = (TextView)view.findViewById(R.id.end_time_smartKey);
        progressBar = (ProgressBar)view.findViewById(R.id.progressbar);
        studioName = (TextView)view.findViewById(R.id.studio_name_smartKey);
        studioLocation = (TextView)view.findViewById(R.id.studio_location_smartKey);
        returnButton = (Button)view.findViewById(R.id.return_button_smartKey);
        nfcCheckBox = (CheckBox)view.findViewById(R.id.nfc_checkbox_smartKey);
        openButton = (LinearLayout)view.findViewById(R.id.open_button_smartKey);
        closeButton = (LinearLayout)view.findViewById(R.id.close_button_smartKey);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("doorlocks").child("3");
    }
}