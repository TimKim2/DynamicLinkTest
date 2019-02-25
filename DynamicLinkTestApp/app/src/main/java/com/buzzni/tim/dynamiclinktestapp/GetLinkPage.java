package com.buzzni.tim.dynamiclinktestapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.buzzni.tim.dynamiclinktestapp.R;

public class GetLinkPage extends AppCompatActivity {

    TextView referer_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_link_page);

        referer_text_view = (TextView)findViewById(R.id.refferer);
    }


}
