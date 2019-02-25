package com.buzzni.tim.dynamiclinktestapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class MainActivity extends AppCompatActivity {

    TextView referer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referer = (TextView)findViewById(R.id.referer);

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            try {
                                deepLink = pendingDynamicLinkData.getLink();
                                String strDeepLink = deepLink.toString();
                                String result[] = strDeepLink.split("hs_deeplink.com?");

                                referer.setText(result[1]);

                                if (result[1].contains("query")) {
                                    String query[] = result[1].split("query=");
                                    String query_value = query[1];

                                    Intent intent = new Intent(getApplicationContext(), QueryActivity.class);
                                    intent.putExtra("query_value", query_value);
                                    startActivity(intent);

                                } else if (result[1].contains("item")) {
                                    String item[] = result[1].split("item=");
                                    String item_id = item[1];

                                    Intent intent = new Intent(getApplicationContext(), entity.class);
                                    intent.putExtra("id_value", item_id);
                                    startActivity(intent);
                                } else if (result[1].contains("url=")) {
                                    String item[] = result[1].split("url=");
                                    String url_parameter = item[1];

                                    Intent intent = new Intent(getApplicationContext(), entity.class);
                                    intent.putExtra("url_value", url_parameter);
                                    startActivity(intent);
                                }
                            }catch (Exception e){


                                referer.setText(pendingDynamicLinkData.getLink().toString());
                            }
                        }

                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                    }
                });
    }

}
