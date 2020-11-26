package com.practice.yeonda_makerthon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityDetailView extends AppCompatActivity {

    private TextView name, location, price, introduce;
    private Button reservationButton;
    private RecyclerView recyclerView;
    private ImageView imageView;

    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> list = new ArrayList<>();
    AdapterDetailViewNotice adapter;
    private StudioTempClass studio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);


        initView();
        setValues();
        setNoticeList();


        // TODO: 2020/11/13
        /***
         * temporary
         * */
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDetailView.this, ActivityReservationSetting.class);
                startActivity(intent);
            }
        });

    }

    private void setValues() {
        name.setText(studio.getName());
        location.setText(studio.getLocation());
        price.setText(studio.getPrice());
        introduce.setText(studio.getIntroduce());
        if(studio.getPosition()==1)
            imageView.setImageResource(R.drawable.studio_src1);
        else if (studio.getPosition() ==2)
            imageView.setImageResource(R.drawable.studio_src2);
        else
            imageView.setImageResource(R.drawable.studio_src3);
    }

    private void setNoticeList() {
        list.add("음식물 반입은 안됩니다.");
        list.add("퇴실 시 잊으신 물건을 확인해주세요. (분실 시 책임지지 않습니다.)");
        list.add("입장 시 손소독 부탁드립니다.");
        list.add("기물 파손 시 변상책임이 있습니다");

        recyclerView.setHasFixedSize(true); // 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setList(list);
        recyclerView.setAdapter(adapter);

    }

    private void initView() {
        Intent intent = getIntent();
        studio = (StudioTempClass) intent.getSerializableExtra("class");

        imageView = (ImageView)findViewById(R.id.imageView_detailView);
        name = (TextView)findViewById(R.id.studio_name_detailView);
        location = (TextView)findViewById(R.id.studio_location_detailView);
        price = (TextView)findViewById(R.id.studio_price_detailView);
        introduce = (TextView)findViewById(R.id.studio_introduce_detailView);
        reservationButton = (Button)findViewById(R.id.reservation_button_detailView);
        recyclerView = (RecyclerView) findViewById(R.id.studio_notice_detailView);
        adapter = new AdapterDetailViewNotice();
    }
}