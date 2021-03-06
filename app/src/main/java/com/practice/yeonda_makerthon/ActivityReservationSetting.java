package com.practice.yeonda_makerthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.listeners.OnMonthChangeListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.model.Month;
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.selection.SingleSelectionManager;
import com.applikeysolutions.cosmocalendar.settings.lists.DisabledDaysCriteria;
import com.applikeysolutions.cosmocalendar.settings.lists.DisabledDaysCriteriaType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityReservationSetting extends AppCompatActivity implements View.OnClickListener {

    private TextView studioName, selectedDate, selectedTime, final_selectedDate, final_selectedTime, final_price;
    private Button[] button = new Button[12];
    private int[] buttonTime ={
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.button10,
            R.id.button11
    };
    private Button oneHourButton, twoHourButton, reservationButton;
    private CalendarView calendarView;
    private LinearLayout selectLayout, finalLayout, onetwoLayout;

    private String selected_time;

    StudioTempClass studio;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef_doorlock;
    int nowReservationSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_setting);

        initView();

        calendarView.setSelectionManager(new SingleSelectionManager(new OnDaySelectedListener() {
            @Override
            public void onDaySelected() {
                allViewInvisible(true);
                selectLayout.setVisibility(View.VISIBLE);

                Calendar calendar = calendarView.getSelectedDates().get(0);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH) + 1;
                final String week = new SimpleDateFormat("EE").format(calendar.getTime());
                selectedDate.setText("" + month + "월 " + day + "일 (" + week + ")");
            }
        }));

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nowReservationSize = (int) snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReservation();
                Intent intent = new Intent(ActivityReservationSetting.this,ActivityMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    void addReservation(){
        BasicReservationClass basicReservationClass = new BasicReservationClass();
        basicReservationClass.setCapacity("4");
        basicReservationClass.setDate("2020-11-27");
        basicReservationClass.setDoorlock_id("3");
        basicReservationClass.setEnd_time(selectedTime.getText().toString().substring(8,13));
        basicReservationClass.setPrice_option_id("1");
        basicReservationClass.setReservation_id(String.valueOf(nowReservationSize+1));
        basicReservationClass.setSpace_id(String.valueOf(studio.getPosition()));
        basicReservationClass.setStart_time(selectedTime.getText().toString().substring(0,5));
        basicReservationClass.setStatus(1);
        basicReservationClass.setUser("913C5155");

        myRef.child(String.valueOf(nowReservationSize+1)).setValue(basicReservationClass);

        myRef_doorlock.child("end_date").setValue(selectedTime.getText().toString().substring(8,13));
        myRef_doorlock.child("start_date").setValue(selectedTime.getText().toString().substring(0,5));
        myRef_doorlock.child("reservation_id").setValue(String.valueOf(nowReservationSize+1));
        myRef_doorlock.child("type").setValue(1);
        myRef_doorlock.child("user_id").setValue("913C5155");

    }


    @Override
    public void onClick(View v) {
        for(int i=0; i<12; i++){
            if(v.getId() == buttonTime[i]){
                selected_time = button[i].getText().toString();
                allViewInvisible(false);
                selectedTime.setVisibility(View.VISIBLE);
                onetwoLayout.setVisibility(View.VISIBLE);
                selectedTime.setText(selected_time);
            }
        }
        if(v.getId() == oneHourButton.getId()){
            String hour_string = selected_time.substring(0,1)+selected_time.substring(1,2);
            int hour_int = Integer.valueOf(hour_string);
            selectedTime.setText(selected_time + " ~ " + (hour_int+1) + ":00");
            finalLayout.setVisibility(View.VISIBLE);
            setFinalText();
        }
        else if(v.getId() == twoHourButton.getId()){
            String hour_string = selected_time.substring(0,1)+selected_time.substring(1,2);
            int hour_int = Integer.valueOf(hour_string);
            selectedTime.setText(selected_time + " ~ " + (hour_int+2) + ":00");
            finalLayout.setVisibility(View.VISIBLE);
            setFinalText();
        }
    }

    private void allViewInvisible(boolean all) {
        if(all)
            selectLayout.setVisibility(View.GONE);
        selectedTime.setVisibility(View.INVISIBLE);
        onetwoLayout.setVisibility(View.GONE);
        finalLayout.setVisibility(View.GONE);
    }

    private void setFinalText() {
        final_selectedDate.setText(selectedDate.getText());
        final_selectedTime.setText(selectedTime.getText());
        // TODO: 2020/11/14
        //final_price.setText();
    }

    private void initView() {
        studioName = (TextView)findViewById(R.id.studio_name_activity_reservation_setting);
        selectedDate = (TextView)findViewById(R.id.select_date_activity_reservation_setting);
        selectedTime = (TextView)findViewById(R.id.select_time_activity_reservation_setting);
        final_selectedDate = (TextView)findViewById(R.id.final_select_date_activity_reservation_setting);
        final_selectedTime = (TextView)findViewById(R.id.final_select_time_activity_reservation_setting);
        final_price = (TextView)findViewById(R.id.final_select_price_activity_setting);
        oneHourButton = (Button)findViewById(R.id.one_hour_activity_reservation_setting);
        twoHourButton = (Button)findViewById(R.id.two_hour_activity_reservation_setting);
        reservationButton = (Button)findViewById(R.id.reservation_button_activity_reservation_setting);
        calendarView = (CalendarView)findViewById(R.id.calendar_activity_reservation_setting);

        selectLayout = (LinearLayout)findViewById(R.id.date_time_setting_layout_act_reservation_setting);
        finalLayout = (LinearLayout)findViewById(R.id.final_select_act_reservation_setting);
        onetwoLayout = (LinearLayout)findViewById(R.id.one_two_layout_act_res_set);

        for(int i=0; i<12; i++){
            button[i] = (Button)findViewById(buttonTime[i]);
            button[i].setOnClickListener(this);
        }
        oneHourButton.setOnClickListener(this);
        twoHourButton.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("resevations");
        myRef_doorlock = database.getReference("doorlocks").child("3").child("current_key_holder").child("1");

        Intent intent = getIntent();
        studio = (StudioTempClass) intent.getSerializableExtra("class");

        studioName.setText(studio.getName());
    }

}