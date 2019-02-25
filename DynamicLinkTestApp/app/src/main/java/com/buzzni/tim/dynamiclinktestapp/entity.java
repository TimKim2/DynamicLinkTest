package com.buzzni.tim.dynamiclinktestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class entity extends AppCompatActivity {

    TextView my_entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity);

        Intent intent = getIntent();

        String entity_id = intent.getExtras().getString("id_value");

        my_entity = (TextView)findViewById(R.id.entity_id);

        my_entity.setText(entity_id + "번 상품입니다!");
    }
}
